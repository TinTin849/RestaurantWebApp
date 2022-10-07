package com.tintin.restaurantwebapp.repositories;

import com.tintin.restaurantwebapp.models.ProductInWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<ProductInWarehouse, Integer> {
}
