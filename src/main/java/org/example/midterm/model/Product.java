package org.example.midterm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false, length = 30)
    private String name;

    @Column(name = "price",nullable = false)
    private long price;

    @Column(name ="color", nullable = false)
    private String color;

    @Column(name = "image", nullable = false)
    private  String image;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrdersItem> orderItems;
    // Constructors, getters và setters
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    public Product() {
    }

    public Product(Long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, long price, String color, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.image = image;
    }

    public Product(Long id, String name, long price, String color, String image, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.image = image;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<OrdersItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrdersItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
