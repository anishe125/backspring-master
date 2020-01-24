package pack.apigateway.dto;

public class AggregatedCartItemDro {

    private int id;
    private String name;
    private float price;
    private int actualAmount;
    private int availableAmount;
    private int orderAmount;

    public AggregatedCartItemDro() {}

    public AggregatedCartItemDro(ItemDto itemDto, CartItemDto cartItemDto) {
        this.id = itemDto.getId();
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
        this.actualAmount = itemDto.getActualAmount();
        this.availableAmount = itemDto.getAvailableAmount();
        this.orderAmount = cartItemDto.getAmount();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}