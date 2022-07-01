package framework.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "參數設定表ID")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class SysParaCodeId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sysId;
	private String paraId;
	private String paraCode;
}