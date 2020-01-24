package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pack.dao.OrderDao;
import pack.entity.Order;
import pack.entity.OrderDto;
import pack.entity.OrderItemDto;
import pack.entity.exceptions.DataIntegrityViolationException;
import pack.entity.exceptions.ItemNotFoundException;
import pack.service.rabbitmq.RabbitMQPublisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderDao orderDao;

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    private static List<String> ROUTING_KEYS = Arrays.asList(
            "order.paid",
            "order.cancelled",
            "order.completed"
    );

    @Autowired
    public OrderService(@Qualifier("sqlite") OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDto addOrder(Order order) throws DataIntegrityViolationException {
        return orderDao.addOrder(order);
    }

    public OrderDto getOrderByID(int id) throws ItemNotFoundException {
        Order foundOrder = orderDao.getOrderById(id);
        return new OrderDto(foundOrder, orderDao.getOrderItems(foundOrder));
    }

    public ArrayList<OrderItemDto> getOrderItems(int id) throws ItemNotFoundException {
        return orderDao.getOrderItems(getOrderByID(id));
    }

    public ArrayList<OrderDto> getOrders() {
        return orderDao.getOrders();
    }

    public OrderDto setOrderStatus(int id, String status) throws ItemNotFoundException, DataIntegrityViolationException {
        OrderDto orderDto = orderDao.setOrderStatus(getOrderByID(id), status);

        if (status.equals("COMPLETED") || status.equals("CANCELLED") || status.equals("PAID")) {
            rabbitMQPublisher.publish("orders." + status.toLowerCase(), orderDto);
        }

        return orderDto;
    }

    public OrderDto addItems(int id, OrderItemDto item) throws ItemNotFoundException, DataIntegrityViolationException {
        return orderDao.addItems(getOrderByID(id), item);
    }

}