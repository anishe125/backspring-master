package pack.dto;


import pack.entity.Payment;

public class PaymentDto {
    private int orderId;
    private String status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static PaymentDto fromPayment(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(payment.getOrderId());
        paymentDto.setStatus(payment.getStatus());
        return paymentDto;
    }
}