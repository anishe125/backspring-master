package pack.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pack.dao.ItemDao;
import pack.dto.ItemAmountDto;
import pack.dto.ItemCreationDto;
import pack.dto.ItemDto;
import pack.dto.OrderDto;
import pack.entity.OrderStatus;

import java.util.List;

@Service
public class ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemService(@Qualifier("sqlite") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public ItemDto addItem(ItemCreationDto item) {
        return itemDao.addItem(item);
    }

    public List<ItemDto> getAllItems() {
        return itemDao.getAllItems();
    }

    public ItemDto getItemById(int id) {
        return itemDao.getItemById(id);
    }

    public ItemDto updateItemAmount(int id, ItemAmountDto itemAmountDto) {
        return itemDao.updateItemAmount(id, itemAmountDto);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "order-item")
    public void orderQueueConsumer(OrderDto orderDto) {
        OrderStatus orderStatus = orderDto.getOrderStatus();
        System.out.println(String.format("Order {%d} status changed to {%s}", orderDto.getId(), orderStatus));
        if (orderStatus == OrderStatus.PAID) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("available", -orderItemDto.getAmount()));
            });
        } else if (orderStatus == OrderStatus.CANCELLED) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("available", orderItemDto.getAmount()));
            });
        } else if (orderStatus == OrderStatus.COMPLETED) {
            orderDto.getItems().forEach(orderItemDto -> {
                this.updateItemAmount(orderItemDto.getItemId(), new ItemAmountDto("actual", -orderItemDto.getAmount()));
            });
        }
    }
}