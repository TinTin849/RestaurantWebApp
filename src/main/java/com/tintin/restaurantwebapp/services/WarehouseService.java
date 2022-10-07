package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.ProductInWarehouse;
import com.tintin.restaurantwebapp.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<ProductInWarehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void update(int id, ProductInWarehouse product) {
        ProductInWarehouse productToBeUpdated = warehouseRepository.findById(id).get();

        productToBeUpdated.setAmount(productToBeUpdated.getAmount() - product.getAmount());
    }
}
