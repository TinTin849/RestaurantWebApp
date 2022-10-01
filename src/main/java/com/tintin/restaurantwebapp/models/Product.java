package com.tintin.restaurantwebapp.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "goods")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Product name should not be empty")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<PurchasePrice> purchasePrices;

    @OneToOne(mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private QuantityInStock quantityInStock;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<PurchasePrice> getPurchasePrices() {
        return purchasePrices;
    }

    public void setPurchasePrices(List<PurchasePrice> purchasePrices) {
        this.purchasePrices = purchasePrices;
    }

    public QuantityInStock getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(QuantityInStock quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
