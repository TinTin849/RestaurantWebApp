package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> findById(int id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public void update(int id, Product product) {
        Product productToBeUpdated = productsRepository.findById(id).get();

        product.setId(id);
        product.setQuantityInStock(productToBeUpdated.getQuantityInStock());
    }

    public int findNumber() {
        return (int) productsRepository.count();
    }

    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }

    @Transactional
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void changeAmount(int quantityOfGoods, int id) {
        Product product = productsRepository.findById(id).get();
        product.getQuantityInStock().setAmount(product.getQuantityInStock().getAmount() + quantityOfGoods);

        productsRepository.save(product);
    }
}
