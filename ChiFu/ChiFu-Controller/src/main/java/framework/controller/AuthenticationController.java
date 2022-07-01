package framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.model.CfUserM;
import framework.util.jwt.JWTUtility;
import framework.util.jwt.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/auth/")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtility jwtUtility;
	@Autowired
	UserService userService;
	
	@PostMapping("/refreshToken")
	public void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws StreamWriteException, DatabindException, IOException {
		// 驗證JWT的起點.
		String requestTokenHeader = httpServletRequest.getHeader(jwtUtility.REFRESH_TOKEN_NAME);

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			String refreshToken = requestTokenHeader.substring(7);
			try {
				String email = jwtUtility.getEmailFromToken(refreshToken);
				CfUserM user = userService.findByEmail(email).get();
				String accessToken = jwtUtility.generateJwtToken(user, jwtUtility.JWT_TOKEN_VALIDITY);
				Map<String, String> tokens = new HashMap<>();
				tokens.put(jwtUtility.TOKEN_NAME, accessToken);
				tokens.put(jwtUtility.REFRESH_TOKEN_NAME, refreshToken);
				httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), tokens);
			} catch (Exception e) {
				System.out.println("error:" + e.getMessage());
				httpServletResponse.setHeader("error", e.getMessage());
				httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh tiken is messing");
		}
	}
}