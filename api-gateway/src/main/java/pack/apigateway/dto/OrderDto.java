package pack.apigateway.dto;

import java.util.ArrayList;

public class OrderDto {

    private Integer id;
    private Integer totalAmount;
    private OrderStatus orderStatus;
    private String totalCost;
    private ArrayList<CartItemDto> items;

    public OrderDto() {}

    public OrderDto(Integer id,
                    Integer totalAmount,
                    Integer orderStatus,
                    String totalCost,
                    ArrayList<CartItemDto> items) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalCost = totalCost;
        this.orderStatus = OrderStatus.values()[orderStatus];
    }

    public Integer getId() {
        return id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setItems(ArrayList<CartItemDto> items) {
        this.items = items;
    }

    public ArrayList<CartItemDto> getItems() {
        return items;
    }
}