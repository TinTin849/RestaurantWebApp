package com.tintin.restaurantwebapp.repositories;

import com.tintin.restaurantwebapp.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {
}
