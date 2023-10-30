package com.example.act_non.customer.repository;

import com.example.act_non.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}
