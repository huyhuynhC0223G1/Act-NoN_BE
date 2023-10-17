package com.example.act_non.order.controller;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.customer.service.ICustomerService;
import com.example.act_non.order.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartDetailController {
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/add/{productId}/{quantity}")
    public String addProductToCart(@PathVariable("productId") Long productId,
                                   @PathVariable("quantity") Long quantity,
                                   @AuthenticationPrincipal Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must login to add this product to your shopping cart.";
        }
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        Long addQuantity = cartDetailService.addQuantityCart(productId, quantity, customer);
        return addQuantity + "item of this product were added to your shopping cart.";
    }
    @PostMapping("/add/{productId}/{quantity}")
    public String updateQuantity(@PathVariable("productId") Long productId,
                                   @PathVariable("quantity") Long quantity,
                                   @AuthenticationPrincipal Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "You must login to add this product to your shopping cart.";
        }
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        if (customer == null){
            return "You must login to add this product to your shopping cart.";
        }
        Double subTotal = cartDetailService.updateQuantity(productId, quantity, customer);
        return String.valueOf(subTotal);
    }
}
