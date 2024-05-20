package com.js.ems.security.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.js.ems.security.service.impl.EmsUserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class EmsSecurityConfiguration {

	@Bean
	public UserDetailsService userDetailsService() {
		return new EmsUserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider ssrsDaoAuthenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers("/", "/employee/list", "/employee/sort", "/employee/save", "/employee/search",
						"/employee/view")
				.hasAnyAuthority("USER", "ADMIN").requestMatchers("/employee/update", "/employee/delete")
				.hasAuthority("ADMIN")
				.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**",
						"/webjars/**")
				.hasAnyAuthority("USER", "ADMIN").requestMatchers("/403").hasAnyAuthority("USER", "ADMIN").anyRequest()
				.hasAuthority("ADMIN") // .authenticated()
				.and().formLogin().loginProcessingUrl("/login").successHandler(new CustomAuthenticationSuccessHandler())
				.permitAll() // .successForwardUrl("/employee/list").permitAll()
				.and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling()
				.accessDeniedHandler(new CustomAccessDeniedHandler()).and().cors().and().csrf().disable();

		http.authenticationProvider(ssrsDaoAuthenticationProvider());
		return http.build();
	}

	// Inner class to handle authentication success
	public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException {
			// Redirect to Swagger UI after successful login
			response.sendRedirect("/swagger-ui.html");
		}
	}

	// Inner class to handle access denied situations
	@Component
	public static class CustomAccessDeniedHandler implements AccessDeniedHandler {

		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException {
			response.sendRedirect("/403");
		}
	}

}
