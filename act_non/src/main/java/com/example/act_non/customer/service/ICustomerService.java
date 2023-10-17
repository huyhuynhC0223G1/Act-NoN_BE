package com.example.act_non.customer.service;

import com.example.act_non.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService {
    Customer findById(Long id);
    Customer GetCurrentlyLoggedInCustomer(Authentication authentication);
}
