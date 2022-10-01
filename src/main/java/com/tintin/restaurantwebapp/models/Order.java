package com.tintin.restaurantwebapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1)
    @Column(name = "quantity_of_goods")
    private int quantityOfGoods;

    @NotNull
    @Column(name = "full_price")
    private double fullPrice;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    public Order() {
    }

    public Order(int quantityOfGoods, double fullPrice, Date creationDate, Product product, Supplier supplier) {
        this.quantityOfGoods = quantityOfGoods;
        this.fullPrice = fullPrice;
        this.creationDate = creationDate;
        this.product = product;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityOfGoods() {
        return quantityOfGoods;
    }

    public void setQuantityOfGoods(int quantityOfGoods) {
        this.quantityOfGoods = quantityOfGoods;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
