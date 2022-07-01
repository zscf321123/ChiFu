package framework.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import framework.filter.R;
import framework.map.CfUserMDto;
import framework.map.CfUserMapper;
import framework.model.CfUserM;
import framework.util.jwt.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(value = "http://localhost:8080")
@RestController
@Api(tags = "使用者資料", value = "取得使用者相關資料")
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	CfUserMapper cfUserMapper;
	
	@ApiOperation(value = "取得所有使用者資料", notes = "取得所有使用者資料")
	@GetMapping("userList")
	public R userList() {
		try {
			return R.isOk().data(userService.findAll());
		} catch (Exception e) {
			return R.isFail(e);
		}
	}

	@ApiOperation(value = "取得使用者資料", notes = "根據ID來查詢使用者資料")
	@GetMapping("findByUserEmail/{email}")
	public R findByUserEmail(@PathVariable String email) {
		try {
			CfUserM cfUserM = Optional.ofNullable(userService.findByEmail(email)).orElseThrow(() -> new NullPointerException()).get();
			CfUserMDto cfUserMDto = cfUserMapper.cfUserMToCfUserMDTO(cfUserM);
			return R.isOk().data(cfUserMDto);
		} catch (Exception e) {
			return R.isFail(e);
		}
	}

	@ApiOperation(value = "新增使用者資料", notes = "新增使用者資料")
	@PostMapping(value = "addCfUserM", produces = MediaType.APPLICATION_JSON_VALUE)
	public R addUser(@RequestBody CfUserMDto cfUserMDto) {
		try {
			CfUserM cfUserM = cfUserMapper.cfUserDtoToCfUserM(cfUserMDto);
			userService.saveCfUserM(cfUserM);
			return R.isOk().data("新增使用者成功");
		} catch (Exception e) {
			System.out.println(e);
			return R.isFail(e);
		}
	}
}