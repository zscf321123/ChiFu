package framework.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="festival_god")
public class FestivalGod {
	
	@EmbeddedId
	private FestivalGodId id;
	private String prayTime;
	private String remark;
	private String updDate;
	private String creDate;
	
}