package com.tintin.restaurantwebapp.models;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class ProductInWarehouse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private int amount;

    @OneToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private Product product;

    public ProductInWarehouse() {
    }

    public ProductInWarehouse(int amount, Product product) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
