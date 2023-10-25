package com.example.act_non.order.service;

import com.example.act_non.order.model.OrderDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderDetailService {
    void addOrderDetail(Double currentPrice,
                        Integer quality,
                        Long idOrder,
                        Long idProduct);
    List<OrderDetail> getListOrderDetail(Long id);
}
