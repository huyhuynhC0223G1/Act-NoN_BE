package com.example.act_non.order.repository;

import com.example.act_non.order.model.CartDetail;
import com.example.act_non.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdersRepository extends JpaRepository<Orders, Long> {

}
