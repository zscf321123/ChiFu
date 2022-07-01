package framework.service;

import java.util.List;

import com.google.common.base.Optional;

import framework.model.ProductM;
import framework.model.SysParaCode;

public interface ProductMService {
	public List<ProductM> findByProductIdIn(List<Integer> items);
	
	public ProductM findByProductId(int productId);
}
