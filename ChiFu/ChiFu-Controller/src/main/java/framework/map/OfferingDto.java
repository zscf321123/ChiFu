package framework.map;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "OFFERING DTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OfferingDto {
	private String offeringId;
	private int godId;
	private String offeringName;
}