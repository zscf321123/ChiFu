package framework.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "商品Id表")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class ProductMId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int productId;
	private int comId;
	private String categoryId;
}