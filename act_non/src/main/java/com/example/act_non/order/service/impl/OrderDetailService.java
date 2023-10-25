package com.example.act_non.order.service.impl;

import com.example.act_non.order.model.OrderDetail;
import com.example.act_non.order.repository.IOrderDetailRepository;
import com.example.act_non.order.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public void addOrderDetail(Double currentPrice, Integer quantity, Long idOrder, Long idProduct) {
        orderDetailRepository.addOrderDetail(currentPrice, quantity, idOrder, idProduct);
    }

    @Override
    public List<OrderDetail> getListOrderDetail(Long id) {
        return orderDetailRepository.getListOrderDetail(id);
    }
}
