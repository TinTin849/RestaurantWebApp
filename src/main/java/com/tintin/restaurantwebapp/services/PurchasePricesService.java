package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.PurchasePrice;
import com.tintin.restaurantwebapp.repositories.ProductsRepository;
import com.tintin.restaurantwebapp.repositories.PurchasePricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PurchasePricesService {

    private final PurchasePricesRepository purchasePricesRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    public PurchasePricesService(PurchasePricesRepository purchasePricesRepository, ProductsRepository productsRepository) {
        this.purchasePricesRepository = purchasePricesRepository;
        this.productsRepository = productsRepository;
    }

    public List<PurchasePrice> findAll() {
        return purchasePricesRepository.findAll();
    }

    public List<PurchasePrice> getSuppliersAndPricesByProductId(int id) {
        Product chosenProduct = productsRepository.findById(id).get();

        return purchasePricesRepository.findPurchasePricesByProduct(chosenProduct);
    }
}
