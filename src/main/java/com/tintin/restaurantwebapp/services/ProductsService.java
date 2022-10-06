package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Transactional
    public void update(int id, Product product) {
        Product productToBeUpdated = productsRepository.findById(id).get();

        product.setId(id);
        product.setQuantityInStock(productToBeUpdated.getQuantityInStock());
    }
}
