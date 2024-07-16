package com.example.repository;
 
import java.util.List;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.example.model.Product;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value="SELECT distinct p.id, pr.price FROM product p JOIN product_variant pr ON p.id=pr.product_id",nativeQuery=true)
	List<Object[]> findAllagain();
 
    Page<Product> findByCategoryId(long categoryId, Pageable pageable);
 
    
    @Query(value="select p.name, p.description, p.id,p.category_id from product p JOIN product_variant pv on pv.product_id=p.id where pv.color_id=?1", nativeQuery=true)
    Page<Product> filterByColor(@Param("color_id") Long colorid, Pageable pageable);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id from product p JOIN product_variant pv on pv.product_id=p.id where pv.size_id=?1", nativeQuery = true)
    Page<Product> filterBySize(@Param("size_id") Long sizeid, Pageable pageable);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id from product p JOIN product_variant pv on pv.product_id=p.id where pv.color_id=?1 and pv.size_id=?2", nativeQuery = true)
    Page<Product> filterByColorAndSize(@Param("color_id") Long colorid, @Param("size_id") Long sizeid, Pageable pageable);
 
    @Query(value="select p.name, p.description, p.id,p.category_id from product p JOIN product_variant pv on pv.product_id=p.id  where p.category_id=?1 and pv.color_id=?2", nativeQuery=true)
    Page<Product> filterByColorcat(@Param("category_id")Long category_id, @Param("color_id") Long colorid, Pageable pageable);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id  from product p JOIN product_variant pv on pv.product_id=p.id where p.category_id=?1 and pv.size_id=?2", nativeQuery = true)
    Page<Product> filterBySizecat(@Param("category_id")Long category_id, @Param("size_id") Long sizeid, Pageable pageable);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id from product p JOIN product_variant pv on pv.product_id=p.id where p.category_id=?1 and pv.color_id=?2 and pv.size_id=?3", nativeQuery = true)
    Page<Product> filterByColorAndSizecat(@Param("category_id")Long category_id, @Param("color_id") Long colorid, @Param("size_id") Long sizeid, Pageable pageable);
}