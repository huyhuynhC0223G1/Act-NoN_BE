package com.example.act_non.product.repository;

import com.example.act_non.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

@Query(nativeQuery = true, value = "SELECT p.id, p.img, p.name, p.description,p.product_type_id, p.price,p.flag_delete, pt.name\n" +
        "FROM product p\n" +
        "         JOIN product_type pt ON pt.id = p.product_type_id\n" +
        "WHERE p.flag_delete = false\n" +
        "  AND ((p.name LIKE CONCAT('%', :pName, '%') OR :pName IS NULL OR :pName = '')\n" +
        "    AND (\n" +
        "               CASE :price\n" +
        "                   WHEN 0 THEN 1\n" +
        "                   WHEN 1 THEN (p.price >= 1 AND p.price <= 49)\n" +
        "                   WHEN 2 THEN (p.price >= 50 AND p.price <= 99)\n" +
        "                   WHEN 3 THEN (p.price >= 100 AND p.price <= 199)\n" +
        "                   WHEN 4 THEN (p.price >= 200)\n" +
        "                   END\n" +
        "               OR :price IS NULL OR :price = '')\n" +
        "    AND (pt.name LIKE CONCAT('%', :ptName, '%') OR :ptName IS NULL OR :ptName = '')\n" +
        "    AND (p.description LIKE CONCAT('%', :description, '%') OR :description IS NULL OR :description = ''))")
    Page<Product> findAllProduct(Pageable pageable,
                                 @Param("pName") String name,
                                 @Param("price") String price,
                                 @Param("ptName") String typeName,
                                 @Param("description") String description);
}
