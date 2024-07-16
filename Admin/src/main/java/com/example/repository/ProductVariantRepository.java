package com.example.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.ProductVariant;
 
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
 
 
    @Query("SELECT pv FROM ProductVariant pv " +
           "JOIN pv.product p " +
           "JOIN pv.color c " +
           "JOIN pv.size s " +
           "WHERE (:category IS NULL OR p.category.id = :category) " +
           "AND (:color IS NULL OR c.color = :color) " +
           "AND (:size IS NULL OR s.size = :size)")
    List<ProductVariant> searchProductVariants(@Param("category") String category,
                                               @Param("color") String color,
                                               @Param("size") String size);
    
    @Query("SELECT pv.product.id, p.name, SUM(oi.quantity) as totalSold " +
            "FROM OrderItem oi " +
            "JOIN oi.productVariant pv " +
            "JOIN pv.product p " +
            "JOIN Order o ON oi.order.id = o.id " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY pv.product.id, p.name " +
            "ORDER BY totalSold DESC " +
            "LIMIT 5")
     List<Object[]> findTop5ProductsOfTheMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}