package framework.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "FESTICAL節慶表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="festival")
public class Festival {
	
	@Id
	private int festivalId;
	private String festivalDay;
	private String festivalName;
	private String romaName;
	private String festivalDescription;
	private String updDate;
	private String creDate;
	
}