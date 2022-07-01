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

@ApiModel(description = "店家資訊使用表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="company_info")
public class CompanyInfo {
	
	@Id
	private int comId;
	private String name;
	private String assignee;
	private String address;
	private String phone;
	private String uniform_code;
	private String updDate;
	private String creDate;	
	
}