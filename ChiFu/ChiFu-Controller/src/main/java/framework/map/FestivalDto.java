package framework.map;

import java.util.List;

import framework.model.FestivalGod;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "FESTIVAL DTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FestivalDto {
	private int festivalId;
	private String festivalDay;
	private String festivalName;
	private String festivalDescription;
	private List<FestivalGodDto> festivalGod;
	private List<ProductMDto> productM;
}