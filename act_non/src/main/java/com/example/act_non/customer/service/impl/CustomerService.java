package com.example.act_non.customer.service.impl;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.customer.repository.ICustomerRepository;
import com.example.act_non.customer.service.ICustomerService;
import com.example.act_non.order.model.CartDetail;
import com.example.act_non.user.model.UserPrinciple;
import com.example.act_non.user.model.Users;
import com.example.act_non.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }


    @Override
    public Customer GetCurrentlyLoggedInCustomer(Authentication authentication) {
        if (authentication == null) return null;

        Customer customer = null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserPrinciple) {
            UserPrinciple userPrinciple = (UserPrinciple) principal;
            Users users = userRepository.findByUsername(userPrinciple.getUsername());

            if (users != null) {
                customer = users.getCustomer();
                System.out.println(customer);
            }
        }
        return customer;
    }
}
