package com.example.act_non.order.repository;

import com.example.act_non.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Modifying
    @Query(nativeQuery = true,value = "insert into order_detail (current_price, quantity, orders_id, product_id)\n" +
            "            values (:currentPrice,:quality,:idOrder,:idProduct)")
    void addOrderDetail (@Param("currentPrice") Double currentPrice,
                         @Param("quality") Integer quality,
                         @Param("idOrder") Long idOrder,
                         @Param("idProduct") Long idProduct);
    @Query(nativeQuery = true, value = "select *\n" +
            "from order_detail\n" +
            "where orders_id = :orderId")
    List<OrderDetail> getListOrderDetail(@Param("orderId") Long id);
}
