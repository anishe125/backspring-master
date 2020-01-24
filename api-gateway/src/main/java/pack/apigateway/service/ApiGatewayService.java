package pack.apigateway.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pack.apigateway.dto.*;
import pack.apigateway.feign.OrderServiceFeignClient;
import pack.apigateway.feign.ItemServiceFeignClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiGatewayService {

    @Autowired
    private ItemServiceFeignClient itemServiceFeignClient;

    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @Autowired
    public ApiGatewayService() {}

    public AggregatedOrderDto getAggregatedOrder(int id) {
        ResponseEntity<OrderDto> orderDtoResponseEntity = orderServiceFeignClient.getItemById(id);
        OrderDto orderDto = orderDtoResponseEntity.getBody();

        List<AggregatedCartItemDro> items = new ArrayList<>();
        for (CartItemDto cartItemDto : orderDto.getItems()) {
            ItemDto itemDto = itemServiceFeignClient.getItemById(cartItemDto.getItemId()).getBody();
            items.add(new AggregatedCartItemDro(itemDto, cartItemDto));
        }

        return new AggregatedOrderDto(orderDto, items);
    }
}
