package framework.util.filter;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import framework.model.CfUserM;
import framework.util.SpringUser;
import framework.util.jwt.JWTUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	private JWTUtility jwtUtility;

	public JwtLoginFilter(AuthenticationManager authenticationManager, JWTUtility jwtUtility) {
		this.authenticationManager = authenticationManager;
		this.jwtUtility = jwtUtility;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		SpringUser userDetails = (SpringUser) authentication.getPrincipal();
		CfUserM userM = userDetails.getPrincipal();
		String accessToken = jwtUtility.generateJwtToken(userM, jwtUtility.JWT_TOKEN_VALIDITY);
		String refreshToken = jwtUtility.generateJwtToken(userM, jwtUtility.REFRESH_TOKEN_VALIDITY);
		System.out.println("token:" + accessToken);
		String roles = userDetails.getAuthorities().toArray()[0].toString();

		System.out.println("role:" + roles);
		Map<String, String> tokens = new HashMap<>();
		tokens.put(jwtUtility.TOKEN_NAME, accessToken);
		tokens.put(jwtUtility.REFRESH_TOKEN_NAME, refreshToken);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

	@Override
	@ResponseBody
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// 登入驗證比對的終點(失敗)
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		ObjectMapper mapper = new ObjectMapper();
		String j = "{\"status\":\"500\",\"message\":\"Internal Server Error!!!\",\"result\":null}";

		response.getOutputStream().println(mapper.writeValueAsString(j));
		response.setHeader("Message", "Could not validate your email and password!!");
		response.setHeader("Location", "http://localhost:8080/index");
		System.out
				.println("JwtLoginFilter.unsuccessfulAuthentication: Could not validate your username and password!!");
	}
}
