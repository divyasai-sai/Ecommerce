package com.example.repository;

import com.example.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
	
	@Query(value="select pv.id from product_variant pv, product p,color c,size s where pv.product_id=?1 and pv.color_id=?2 and pv.size_id=?3",nativeQuery=true)
	public Long findByProdIdColorIdSizeId(Long prodId,Long colorId,Long sizeId);
}
