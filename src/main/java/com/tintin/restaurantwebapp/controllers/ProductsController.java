package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.ProductInWarehouse;
import com.tintin.restaurantwebapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @RequestMapping()
    public String allProducts(Model model) {
        List<Product> allProducts = productsService.findAll();

        model.addAttribute("products", allProducts);
        model.addAttribute("newProduct", new Product());

        return "products/all-products";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping()
    public String deleteProduct(@ModelAttribute() Product product) {
        productsService.delete(product.getId());

        return "redirect:/products";
    }

    @RequestMapping("/add")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        product.setQuantityInStock(new ProductInWarehouse());
        product.getQuantityInStock().setAmount(0);
        productsService.save(product);

        return "redirect:/products";
    }

//    @RequestMapping("/{id}")
//    public String update(@ModelAttribute("product") Product product,
//                         @PathVariable("id") int id) {
//        productsService.update(id, product);
//
//        return "redirect:/goods";
//    }
}
