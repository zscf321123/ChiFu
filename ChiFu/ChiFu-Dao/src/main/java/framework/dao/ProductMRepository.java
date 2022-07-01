package framework.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;

import framework.model.ProductM;

public interface ProductMRepository extends JpaRepository<ProductM, String> {
	
	@Query(nativeQuery =true,value = "SELECT * FROM product_m WHERE product_id IN (:items)")
	List<ProductM> findByProductIdIn(@Param("items") List<Integer> items);
	
	Optional<ProductM> findByIdProductId(int productId);
}