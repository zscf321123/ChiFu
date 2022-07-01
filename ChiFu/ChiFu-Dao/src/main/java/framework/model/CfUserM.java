package framework.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "CF_USER_M使用者資料表")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name="cf_user_m")
public class CfUserM {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName="cf_user_m_user_id_seq", allocationSize=1)
	private int userId;
	private String email;
	private String password;
	private String type;
	private String userName;
	private String gender;
	private String birthday;
	private String phone;
	private String address;
	private String updDate;
	private String creDate;
	private int version;
//	@ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
	
	
	public CfUserM(String email, String password, String type, String userName, String gender, String birthday, String phone, String address, String updDate, String creDate, int version) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
		this.userName = userName;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.updDate = updDate;
		this.creDate = creDate;
		this.version = version;
	}
	
}