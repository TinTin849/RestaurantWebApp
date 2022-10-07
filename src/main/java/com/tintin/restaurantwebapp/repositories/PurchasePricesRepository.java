package com.tintin.restaurantwebapp.repositories;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.PurchasePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasePricesRepository extends JpaRepository<PurchasePrice, Integer> {
    List<PurchasePrice> findPurchasePricesByProduct(Product product);
}
