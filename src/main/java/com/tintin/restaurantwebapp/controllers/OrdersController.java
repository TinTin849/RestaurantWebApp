package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.Order;
import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.PurchasePrice;
import com.tintin.restaurantwebapp.services.ProductsService;
import com.tintin.restaurantwebapp.services.PurchasePricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final ProductsService productsService;
    private final PurchasePricesService purchasePricesService;

    @Autowired
    public OrdersController(ProductsService productsService, PurchasePricesService purchasePricesService) {
        this.productsService = productsService;
        this.purchasePricesService = purchasePricesService;
    }

    @RequestMapping("/new")
    public String newOrder(@ModelAttribute("product") Product product
            , Model model) {

        List<Product> allProducts = productsService.findAll();
        model.addAttribute("allProducts", allProducts);

        List<PurchasePrice> chosenSuppliersAndPrices = new ArrayList<>();
        if (product.getId() != -1) {
            chosenSuppliersAndPrices = purchasePricesService.getSuppliersAndPricesByProductId(product.getId());
        }
        model.addAttribute("chosenSuppliersAndPrices", chosenSuppliersAndPrices);

        model.addAttribute("newOrder", new Order());

        return "orders/new-order";
    }

    @RequestMapping("/add")
    public String addOrder()
}
