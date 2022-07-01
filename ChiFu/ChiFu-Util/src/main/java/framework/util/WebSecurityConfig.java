package framework.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import framework.util.filter.JwtAuthenticationFilter;
import framework.util.filter.JwtLoginFilter;
import framework.util.jwt.JWTUtility;
import framework.util.jwt.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
    private JWTUtility jwtUtility;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.userService);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authProvider;
	}

	// 如果要對密碼加密
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(authenticationManagerBean(),jwtUtility);
		jwtLoginFilter.setFilterProcessesUrl("/login");
		http.csrf().disable()// 因為是做 token 驗證，不用開啟，避免 csrf
				.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().
				antMatchers(HttpMethod.GET, "/user/**").authenticated().
				antMatchers(HttpMethod.GET).permitAll().
				antMatchers(HttpMethod.POST, "/login").permitAll().
				antMatchers(HttpMethod.POST, "/user/**").permitAll().
				antMatchers(HttpMethod.POST, "/auth/**").permitAll().
				antMatchers(HttpMethod.POST, "/festival/**").permitAll().anyRequest().authenticated()
				.and().addFilter(jwtLoginFilter)// 添加針對login過濾器進行JWT驗證，並進行導頁
				.addFilterBefore(jwtAuthenticationFilter, // new JwtAuthenticationFilter()
						UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 這裡的設定將會使得 UserDetailsServiceImpl.loadUserByUsername被呼叫
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}