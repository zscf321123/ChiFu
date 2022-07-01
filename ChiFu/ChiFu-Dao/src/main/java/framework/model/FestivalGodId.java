package framework.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "FESTICAL節慶表")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class FestivalGodId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int festivalId;
	private int godId;
	private String godName;
}