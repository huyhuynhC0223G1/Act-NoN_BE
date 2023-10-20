package com.example.act_non.order.repository;

import com.example.act_non.customer.model.Customer;
import com.example.act_non.order.model.CartDetail;
import com.example.act_non.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM cart_detail cd \n" +
            "         JOIN product p ON p.id = cd.product_id \n" +
            "         JOIN customer c ON c.id = cd.customer_id \n" +
            "WHERE flag_delete = false AND cd.customer_id = :customer \n" +
            "GROUP BY cd.id \n" +
            "ORDER BY cd.id DESC")
    List<CartDetail> findByCustomer(@Param("customer") Long customerId);
    CartDetail findByCustomerAndProduct(Customer customer, Product product);
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE cart_detail\n" +
            "set quantity = :quantity\n" +
            "where product_id = :productId\n" +
            "  and customer_id = :customerId ")
    void updateQuantity(@Param("quantity") Long quantity,
                              @Param("productId") Long productId,
                              @Param("customerId") Long customerId);
}
