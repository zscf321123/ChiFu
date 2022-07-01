package framework.map;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "FESTIVAL_GOD DTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class FestivalGodDto {
	private int festivalId;
	private int godId;
	private String godName;
	private String prayTime;
	private String remark;
	private List<OfferingDto> offering;

}