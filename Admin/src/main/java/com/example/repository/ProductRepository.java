package com.example.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
 
import com.example.model.Product;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 
    List<Product> findByCategoryId( long categoryId);
      
    @Query(value="select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id where pv.color_id=?1;", nativeQuery=true)
    List<Product> filterByColor( @Param("color_id")Long colorid);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id where pv.size_id=?1;", nativeQuery = true)
    List<Product> filterBySize( @Param("size_id") Long sizeid);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id where pv.color_id=?1 and pv.size_id=?2;", nativeQuery = true)
    List<Product> filterByColorAndSize( @Param("color_id") Long colorid, @Param("size_id") Long sizeid);
    
    @Query(value="select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id  where p.category_id=?1 and pv.color_id=?2;", nativeQuery=true)
    List<Product> filterByColorcat(Long category_id, @Param("color_id")Long colorid);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id where p.category_id=?1 and pv.size_id=?2;", nativeQuery = true)
    List<Product> filterBySizecat(Long category_id, @Param("size_id") Long sizeid);
 
    @Query(value = "select p.name, p.description, p.id,p.category_id,p.image_url from product p JOIN product_variant pv on pv.product_id=p.id where p.category_id=?1 and pv.color_id=?2 and pv.size_id=?3;", nativeQuery = true)
    List<Product> filterByColorAndSizecat(Long category_id, @Param("color_id") Long colorid, @Param("size_id") Long sizeid);
 
 
 
}