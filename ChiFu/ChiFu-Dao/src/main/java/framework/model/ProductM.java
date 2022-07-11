package framework.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "商品表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="product_m")
public class ProductM {

	@EmbeddedId
	private ProductMId id;
	private String productName;
	private String productSubName;
	private int price;
	private int specialPrice;
	private String spec;
	private int storage;
	private String updDate;
	private String creDate;
	
}