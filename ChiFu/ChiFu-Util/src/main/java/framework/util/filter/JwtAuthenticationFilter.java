package framework.util.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import framework.util.jwt.JWTUtility;
import framework.util.jwt.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
// 與 UsernamePasswordAuthenticationFilter 不同，BasicAuthenticationFilter 沒有繼承 AbstractAuthenticationProcessingFilter，
// 而是直接繼承 OncePerRequestFilter。因為它是被使用在請求業務 API 的請求上，而不是進行身份認證流程。
// 無論認證成功與否，BasicAuthenticationFilter 都不會做出重定向的響應。
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtility jwtUtility;

	@Lazy
	@Autowired
	private UserService userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		// 驗證JWT的起點.
		String requestTokenHeader = httpServletRequest.getHeader(jwtUtility.TOKEN_NAME);

		String token = null;
		String email = null;
		String userId = null;
		String role = null;
		System.out.println("doFilterInternal requestTokenHeader:" + requestTokenHeader);

		if (httpServletRequest.getServletPath().equals("/chi/login") || httpServletRequest.getServletPath().equals("/chi/auth/refreshToken")) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				token = requestTokenHeader.substring(7);
				logger.info(token);
				try {
					email = jwtUtility.getEmailFromToken(token);
					userId = jwtUtility.getUserIdFromToken(token);
					role = jwtUtility.getRoleIdFromToken(token);
					System.out.println("filter email:" + email);
					System.out.println("filter user id:" + userId);
					System.out.println("filter role id:" + role);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					authorities.add(new SimpleGrantedAuthority(role));
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							email, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					filterChain.doFilter(httpServletRequest, httpServletResponse);
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
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}
		}
	}
}