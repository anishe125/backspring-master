package pack.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {
    private int orderId;
    private String status;
    private String userName;

    public Payment(
            @JsonProperty("orderId") int orderId,
            @JsonProperty("status") String status,
            String userName) {
        this.orderId = orderId;
        this.status = status;
        this.userName = userName;
    }

    public Payment() {}

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
