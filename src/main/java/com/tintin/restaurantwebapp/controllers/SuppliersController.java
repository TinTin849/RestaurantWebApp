package com.tintin.restaurantwebapp.controllers;

import com.tintin.restaurantwebapp.models.Product;
import com.tintin.restaurantwebapp.models.PurchasePrice;
import com.tintin.restaurantwebapp.models.Supplier;
import com.tintin.restaurantwebapp.services.ProductsService;
import com.tintin.restaurantwebapp.services.PurchasePricesService;
import com.tintin.restaurantwebapp.services.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/suppliers")
public class SuppliersController {

    private final SuppliersService suppliersService;
    private final PurchasePricesService purchasePricesService;
    private final ProductsService productsService;

    @Autowired
    public SuppliersController(SuppliersService suppliersService, PurchasePricesService purchasePricesService, ProductsService productsService) {
        this.suppliersService = suppliersService;
        this.purchasePricesService = purchasePricesService;
        this.productsService = productsService;
    }

    @RequestMapping()
    public String allSuppliers(Model model) {
        List<Supplier> allSuppliers = suppliersService.findAll();

        model.addAttribute("suppliers", allSuppliers);
        model.addAttribute("newSupplier", new Supplier());

        return "suppliers/all-suppliers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping()
    public String deleteSupplier(@ModelAttribute() Supplier supplier) {
        suppliersService.delete(supplier.getId());

        return "redirect:/suppliers";
    }

    @RequestMapping("/add")
    public String addSupplier(@ModelAttribute("newSupplier") Supplier supplier) {
        suppliersService.save(supplier);

        return "redirect:/suppliers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/{id}")
    public String editSupplier(@PathVariable("id") int id
            , Model model) {
        Supplier supplierToEdit = suppliersService.findById(id).get();
        model.addAttribute("supplier", supplierToEdit);

        PurchasePrice purchasePrice = new PurchasePrice();
        purchasePrice.setSupplier(supplierToEdit);
        model.addAttribute("newPurchasePrice", purchasePrice);

        List<Product> unusedProducts = getUnusedProducts(supplierToEdit);
        model.addAttribute("unusedProducts", unusedProducts);

        return "suppliers/edit-supplier";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/product")
    public String updatePrice(@ModelAttribute PurchasePrice purchasePrice) {

        double newPrice = purchasePrice.getPrice();
        purchasePrice = purchasePricesService.getPurchasePriceById(purchasePrice.getId());
        purchasePrice.setPrice(newPrice);

        purchasePricesService.save(purchasePrice);

        return "redirect:/suppliers/" + purchasePrice.getSupplier().getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product")
    public String deleteProductFromSupplier(@ModelAttribute PurchasePrice purchasePrice) {
        int idToBeDeleted = purchasePrice.getId();

        purchasePricesService.delete(idToBeDeleted);

        return "redirect:/suppliers/" + purchasePrice.getSupplier().getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product")
    public String addProductToSupplier(@ModelAttribute("newPurchasePrice") PurchasePrice purchasePrice) {
        Product product = productsService.findById(purchasePrice.getProduct().getId()).get();
        purchasePrice.setProduct(product);

        Supplier supplier = suppliersService.findById(purchasePrice.getSupplier().getId()).get();
        purchasePrice.setSupplier(supplier);

        purchasePricesService.save(purchasePrice);

        return "redirect:/suppliers/" + purchasePrice.getSupplier().getId();
    }

    private List<Product> getUnusedProducts(Supplier supplier) {
        List<Product> usedProduct = supplier.getPurchasePrices().stream()
                .map(PurchasePrice::getProduct)
                .collect(Collectors.toList());
        List<Product> allProducts = productsService.findAll();

        allProducts.removeAll(usedProduct);

        return allProducts;
    }
}
