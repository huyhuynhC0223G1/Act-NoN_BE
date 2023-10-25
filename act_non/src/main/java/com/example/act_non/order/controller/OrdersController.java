package com.example.act_non.order.controller;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.customer.service.ICustomerService;
import com.example.act_non.order.model.IOrderProjection;
import com.example.act_non.order.model.Orders;
import com.example.act_non.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrders(@RequestParam("totalPrice") Double totalPrice,
                                       Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return new ResponseEntity<>("You must login to add this product to your shopping cart.", HttpStatus.UNAUTHORIZED);
        }
        String username = authentication.getName();
        // Thực hiện các thao tác thêm sản phẩm vào giỏ hàng
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);

        Orders orders = orderService.addOrder(customer.getId(), totalPrice);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @GetMapping("/history")
    public ResponseEntity<?> getListHistory(@RequestParam("page") Integer page,
                                            @RequestParam(value = "startDate", required = false) String startDate,
                                            @RequestParam(value = "endDate", required = false) String endDate,
                                            Authentication authentication) {
        if (startDate != null && startDate.isEmpty()) {
            startDate = null;
        }

        if (endDate != null && endDate.isEmpty()) {
            endDate = null;
        }

        Pageable pageable = PageRequest.of(page, 50);
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        if (customer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderService.getListOrder(pageable, customer.getId(), startDate, endDate), HttpStatus.OK);
        }
    }

    @GetMapping("/his")
    public ResponseEntity<Page<Orders>> getListHistoryTest(@RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "size") Integer size,
                                                           Authentication authentication) {

        Pageable pageable = PageRequest.of(page, size);
        Customer customer = customerService.GetCurrentlyLoggedInCustomer(authentication);
        if (customer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(orderService.getListOrderTest(pageable, customer.getId()), HttpStatus.OK);
        }
    }
}
