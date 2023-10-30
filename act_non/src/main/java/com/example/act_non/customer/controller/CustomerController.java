package com.example.act_non.customer.controller;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("")
    public ResponseEntity<?> getCustomerByID(Authentication authentication){
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("You must login to add this product to your shopping cart.", HttpStatus.UNAUTHORIZED);
        }
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
    return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
