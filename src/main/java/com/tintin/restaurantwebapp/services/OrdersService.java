package com.tintin.restaurantwebapp.services;

import com.tintin.restaurantwebapp.models.Order;
import com.tintin.restaurantwebapp.repositories.OrdersRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public Optional<Order> findById(int id) {
        return ordersRepository.findById(id);
    }

    public void save(Order order) {
        ordersRepository.save(order);
    }
}
