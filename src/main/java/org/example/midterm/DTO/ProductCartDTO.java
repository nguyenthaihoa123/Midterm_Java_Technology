package org.example.midterm.DTO;

public class ProductCartDTO {

    private Long id;
    private String name;
    private String color;
    private long price;
    private int quantity;

    private String img;

    public ProductCartDTO() {
    }

    public ProductCartDTO(Long id, String name, String color, long price, int quantity, String img) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
