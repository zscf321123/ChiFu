package framework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import framework.dao.ProductMRepository;
import framework.model.ProductM;
import framework.service.ProductMService;

@Service
public class ProductMServiceImpl implements ProductMService {
	@Autowired
	ProductMRepository productMRepository;

	@Override
	public List<ProductM> findByProductIdIn(List<Integer> items) {
		return productMRepository.findByProductIdIn(items);
	}

	@Override
	public ProductM findByProductId(int productId) {
		Optional<ProductM> productOptional = productMRepository.findByIdProductId(productId);
		return productOptional.isPresent()?productOptional.get():null;
	}


}