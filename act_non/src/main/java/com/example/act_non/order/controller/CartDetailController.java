package com.example.act_non.order.controller;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.customer.service.ICustomerService;
import com.example.act_non.order.model.CartDetail;
import com.example.act_non.order.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartDetail")
@CrossOrigin("*")
public class CartDetailController {
    @Autowired
    private ICartDetailService cartDetailService;
    @Autowired
    private ICustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> getCartList(Authentication authentication) {
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        List<CartDetail> cartDetail = cartDetailService.cartDetailList(customer.getId());
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }


    @PostMapping("/add/{productId}/{quantity}")
    public ResponseEntity<?> addProductToCart(@PathVariable("productId") Long productId,
                                              @PathVariable("quantity") Long quantity,
                                              Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("You must login to add this product to your shopping cart.", HttpStatus.UNAUTHORIZED);
        }
        String username = authentication.getName();
        System.out.println(username);
        // Thực hiện các thao tác thêm sản phẩm vào giỏ hàng
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        System.out.println(customer);
        Long addQuantity = cartDetailService.addQuantityCart(productId, quantity, customer);

        return new ResponseEntity<>(addQuantity + " item(s) of this product were added to your shopping cart.", HttpStatus.CREATED);
    }

    @PostMapping("/update/{productId}/{quantity}")
    public ResponseEntity<?> updateQuantity(@PathVariable("productId") Long productId,
                                 @PathVariable("quantity") Long quantity,
                                 Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("You must login to add this product to your shopping cart.", HttpStatus.UNAUTHORIZED);
        }
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        if (customer == null) {
            return new ResponseEntity<>("You must login to add this product to your shopping cart.", HttpStatus.UNAUTHORIZED);
        }
        Double subTotal = cartDetailService.updateQuantity(productId, quantity, customer);
        return new ResponseEntity<>(subTotal + " item(s) of this product were added to your shopping cart.", HttpStatus.CREATED);
    }
}
