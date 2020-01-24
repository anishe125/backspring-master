package pack.dto;


import pack.entity.Item;

public class ItemCreationDto {
    private String name;
    private float price;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item toItem() {
        Item item = new Item();
        item.setName(this.getName());
        item.setPrice(this.getPrice());
        item.setActualAmount(this.getAmount());
        item.setAvailableAmount(this.getAmount());
        return item;
    }
}