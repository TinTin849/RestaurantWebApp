package com.tintin.restaurantwebapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goods_supplier",
        uniqueConstraints = @UniqueConstraint(
                columnNames={"goods_id", "supplier_id"}
        ))
public class PurchasePrice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    public PurchasePrice() {
    }

    public PurchasePrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
