package com.example.techub.techubStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.techub.techubStore.security.JwtAuthFilter;
import com.example.techub.techubStore.security.JwtService;
import com.example.techub.techubStore.service.impl.UserServiceImpl;


@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter(){
	        return new JwtAuthFilter(jwtService, userService);
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .userDetailsService(userService)
        .passwordEncoder(passwordEncode());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http
         .csrf().disable()
         .authorizeRequests()
         	 .antMatchers("/api/clients/**")
         		.hasAnyRole("USER", "ADMIN")
         	 .antMatchers(HttpMethod.GET, "/api/users/**")
         	 	.hasRole("ADMIN")
             .antMatchers(HttpMethod.POST, "/api/users/**")
                 .permitAll()
             .anyRequest().authenticated()
         .and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
             .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
