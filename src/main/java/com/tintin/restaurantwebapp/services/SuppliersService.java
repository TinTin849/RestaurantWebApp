package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Supplier;
import com.tintin.restaurantwebapp.repositories.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class SuppliersService {

    private final SuppliersRepository suppliersRepository;

    @Autowired
    public SuppliersService(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    public Optional<Supplier> findById(int id) {
        return suppliersRepository.findById(id);
    }

    public int findNumber() {
        return (int) suppliersRepository.count();
    }

    public List<Supplier> findAll() {
        return suppliersRepository.findAll();
    }

    @Transactional
    public void delete(int id) {
        suppliersRepository.deleteById(id);
    }

    @Transactional
    public void save(Supplier supplier) {
        suppliersRepository.save(supplier);
    }
}
