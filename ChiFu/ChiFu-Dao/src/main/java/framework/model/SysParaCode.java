package framework.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "參數設定表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="sys_para_code")
public class SysParaCode {
	
	@EmbeddedId
	private SysParaCodeId id;
	private String note;
	private String paraValue;
	
}