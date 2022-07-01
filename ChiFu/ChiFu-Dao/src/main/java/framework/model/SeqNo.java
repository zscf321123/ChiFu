package framework.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "SEQ_NO代碼編號表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="seq_no")
public class SeqNo {
	
	@Id
	private String moduleNo;
	private int seq;
//	@ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
	
}