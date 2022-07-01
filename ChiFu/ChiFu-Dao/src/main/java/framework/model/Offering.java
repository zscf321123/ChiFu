package framework.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "節慶神明表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="offering")
public class Offering {
	
	@EmbeddedId
	private OfferingId id;
	private String updDate;
	private String creDate;
	
}