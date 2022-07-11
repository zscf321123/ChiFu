package framework.map;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import framework.model.ProductM;

@Mapper
public interface ProductMMapper {
	@Mapping(source = "comId", target = "id.comId")
	ProductM productMDtoToProductM(ProductMDto productMDto);

	@Mapping(source = "id.comId", target = "comId")
	ProductMDto productMToProductMDto(ProductM productM);
	
	List<ProductM> productMList(List<ProductMDto> productMDtos);
}
