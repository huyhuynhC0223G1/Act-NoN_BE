package com.example.act_non.order.service;

import com.example.act_non.order.model.IOrderProjection;
import com.example.act_non.order.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Orders addOrder(Long idCustomer, Double totalPrice);
    Page<Orders> getListOrder(Pageable pageable,
                                          Long idCustomer,
                                          String startDate,
                                          String endDate);
    Page<Orders> getListOrderTest(Pageable pageable,
                                        Long idCustomer
                                            );

}

