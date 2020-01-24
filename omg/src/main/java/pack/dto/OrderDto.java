package pack.dto;



import pack.entity.Order;

import java.util.ArrayList;

public class OrderDto extends Order {

    public ArrayList<CartItemDto> getItems() {
        return items;
    }

    private ArrayList<CartItemDto> items;

    public OrderDto() {
        super();
    }

    public OrderDto(Order order, ArrayList<CartItemDto> items){
        super(order.getId(), order.getTotalAmount(), order.getOrderStatus().ordinal(), String.valueOf(order.totalCost));
        this.items = items;
    }
}