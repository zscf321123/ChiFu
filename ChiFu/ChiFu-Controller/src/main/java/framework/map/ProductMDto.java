package framework.map;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "PRODUCT_M DTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductMDto {
	private String productName;
	private String spec;
	private int comId;
	private String companyName;
	private int price;
}