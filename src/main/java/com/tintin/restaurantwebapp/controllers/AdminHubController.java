package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.services.OrdersService;
import com.tintin.restaurantwebapp.services.ProductsService;
import com.tintin.restaurantwebapp.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHubController {

    private final ProductsService productsService;
    private final OrdersService ordersService;
    private final SuppliersService suppliersService;

    @Autowired
    public AdminHubController(ProductsService productsService, OrdersService ordersService, SuppliersService suppliersService) {
        this.productsService = productsService;
        this.ordersService = ordersService;
        this.suppliersService = suppliersService;
    }

    @RequestMapping()
    public String aminInfo(Model model) {

        int numberOfProducts = productsService.findNumber();
        int numberOfSuppliers = suppliersService.findNumber();
        int numberOfOrders = ordersService.findNumber();

        model.addAttribute("numberOfProducts", numberOfProducts);
        model.addAttribute("numberOfSuppliers", numberOfSuppliers);
        model.addAttribute("numberOfOrders", numberOfOrders);

        return "admin-hub";
    }
}
