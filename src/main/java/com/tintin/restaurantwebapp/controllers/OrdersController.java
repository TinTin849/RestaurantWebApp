package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.Order;
import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.PurchasePrice;
import com.tintin.restaurantwebapp.models.Supplier;
import com.tintin.restaurantwebapp.services.OrdersService;
import com.tintin.restaurantwebapp.services.ProductsService;
import com.tintin.restaurantwebapp.services.PurchasePricesService;
import com.tintin.restaurantwebapp.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final ProductsService productsService;
    private final PurchasePricesService purchasePricesService;
    private final OrdersService ordersService;
    private final SuppliersService suppliersService;

    @Autowired
    public OrdersController(ProductsService productsService, PurchasePricesService purchasePricesService, OrdersService ordersService, SuppliersService suppliersService) {
        this.productsService = productsService;
        this.purchasePricesService = purchasePricesService;
        this.ordersService = ordersService;
        this.suppliersService = suppliersService;
    }

    @RequestMapping()
    public String allOrders(Model model) {
        List<Order> allOrders = ordersService.findAll();
        model.addAttribute("allOrders", allOrders);

        return "orders/all";
    }

    @RequestMapping("/new")
    public String newOrder(@ModelAttribute("product") Product product
            , Model model) {
        List<Product> allProducts = productsService.findAll();
        model.addAttribute("allProducts", allProducts);

        List<PurchasePrice> pricesAndSuppliers = new ArrayList<>();
        if (product.getId() != -1 && product.getId() != 0) {
            pricesAndSuppliers = purchasePricesService.getSuppliersAndPricesByProductId(product.getId());
        }
        model.addAttribute("pricesAndSuppliers", pricesAndSuppliers);

        model.addAttribute("newOrder", new Order());
        return "orders/new-order";
    }

    @RequestMapping("/add")
    public String addOrder(@ModelAttribute("newOrder") Order order
            , Model model) {
        Product product = productsService.findById(order.getProduct().getId()).get();
        order.setProduct(product);

        Supplier supplier = suppliersService.findById(order.getSupplier().getId()).get();
        order.setSupplier(supplier);

        order.setFullPrice(order.getQuantityOfGoods() * order.getFullPrice());

        order.setCreationDate(new Date());

        ordersService.save(order);

        return "redirect:/orders";
    }
}
