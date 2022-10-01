package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Product> allProducts = productsService.findAll();

        model.addAttribute("products", allProducts);

        return "products/index";
    }
}
