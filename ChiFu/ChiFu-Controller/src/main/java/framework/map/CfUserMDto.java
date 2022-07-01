package framework.map;

import java.util.List;

import framework.model.UserAuthority;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "CF_USER_M DTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CfUserMDto {
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
}