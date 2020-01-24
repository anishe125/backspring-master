package pack.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    public Integer getId() {
        return id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public double getTotalCost() {
        return Double.parseDouble(totalCost);
    }

    private Integer id;
    private Integer totalAmount;
    private OrderStatus orderStatus;
    public String totalCost;

    public Order() {}

    public Order(Integer id,
                 Integer totalAmount,
                 Integer orderStatus,
                 String totalCost) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.totalCost = totalCost;
        this.orderStatus = OrderStatus.values()[orderStatus];
    }
}
