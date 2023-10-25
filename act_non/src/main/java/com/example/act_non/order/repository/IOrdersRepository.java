package com.example.act_non.order.repository;


import com.example.act_non.order.model.IOrderProjection;
import com.example.act_non.order.model.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    @Query(nativeQuery = true,
            value = "select *\n" +
                    "from orders\n" +
                    "where customer_id = :idCustomer\n" +
                    "     AND (DATE(date_created) >= :startDate OR :startDate IS NULL)\n" +
                    "     AND (DATE(date_created) <= :endDate OR :endDate IS NULL)\n" +
                    "    ORDER BY orders.date_created DESC")
    Page<Orders> getListOrder(Pageable pageable, @Param("idCustomer") Long idCustomer,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);
    @Query(nativeQuery = true,
            value = "SELECT *\n" +
                    "FROM orders\n" +
                    "WHERE customer_id = :idCustomer\n" +
                    "ORDER BY id DESC")
    Page<Orders> getListOrderTest(Pageable pageable, @Param("idCustomer") Long idCustomer);
}
