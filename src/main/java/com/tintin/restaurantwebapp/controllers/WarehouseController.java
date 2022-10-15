package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.ProductInWarehouse;
import com.tintin.restaurantwebapp.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping()
    public String index(Model model) {
        List<ProductInWarehouse> allProducts = warehouseService.findAll();

        model.addAttribute("warehouseElements", allProducts);
        model.addAttribute("editableElement", new ProductInWarehouse());

        return "warehouse/index";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("editableElement") ProductInWarehouse product,
                         @PathVariable("id") int id) {
        warehouseService.update(id, product);

        return "redirect:/warehouse";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/fix")
    public String fixWarehouse(Model model) {
        List<ProductInWarehouse> allProducts = warehouseService.findAll();

        model.addAttribute("warehouseElements", allProducts);

        return "warehouse/fix";
    }
}
