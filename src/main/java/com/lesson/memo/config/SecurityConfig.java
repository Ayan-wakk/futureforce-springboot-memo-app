package com.lesson.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lesson.memo.security.AdminDetailService;

@Configuration
public class SecurityConfig{
	
	@Autowired
	private AdminDetailService adminDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		    .authorizeHttpRequests(auth -> auth
		        .requestMatchers(
		            "/admin/signup",
		            "/admin/signin",
		            "/css/**"
		        ).permitAll().anyRequest().authenticated()
		    )
		    .formLogin(form -> form
		        .loginPage("/admin/signin")
		        .loginProcessingUrl("/admin/signin")
		        .usernameParameter("email")
		        .defaultSuccessUrl("/memo", true)
		        .permitAll()
		    )
		
		    .logout(logout -> logout
		        .logoutSuccessUrl("/admin/signin")
		        .permitAll()
		    );
		
		return http.build();
	}
}
