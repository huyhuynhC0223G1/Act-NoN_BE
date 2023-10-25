package com.example.act_non.order.service.impl;

import com.example.act_non.customer.repository.ICustomerRepository;
import com.example.act_non.customer.service.ICustomerService;
import com.example.act_non.order.model.IOrderProjection;
import com.example.act_non.order.model.Orders;
import com.example.act_non.order.repository.IOrdersRepository;
import com.example.act_non.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IOrdersRepository ordersRepository;
    @Override
    public Orders addOrder(Long idCustomer, Double totalPrice) {
        Orders orders = new Orders();
        orders.setTotalPrice(totalPrice);
        orders.setDateCreated(LocalDateTime.now());
        orders.setCustomer(customerRepository.findById(idCustomer).get());
        orders.setFlagDeleted(false);
        Orders orders1 =  ordersRepository.save(orders);
        return orders1;
    }

    @Override
    public Page<Orders> getListOrder(Pageable pageable, Long idCustomer, String startDate, String endDate) {
        return ordersRepository.getListOrder(pageable, idCustomer,startDate, endDate);
    }

    @Override
    public Page<Orders> getListOrderTest(Pageable pageable, Long idCustomer) {
        return ordersRepository.getListOrderTest(pageable,idCustomer);
    }
}
