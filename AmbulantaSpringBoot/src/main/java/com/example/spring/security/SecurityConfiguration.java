package com.example.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		    .antMatchers("/Autentifikacija/**","/")
		    .permitAll()
		    .antMatchers("/Farmaceut/**","/farmaceut/**")
		    .hasRole("farmaceut")
	        .antMatchers("/Admin/**")
	        .access("hasRole('admin')")
	        .antMatchers("/Doktor/**","/doktor/**")
	        .hasRole("doktor")
	        .and()
	        .formLogin()
	        .loginPage("/Autentifikacija/loginPage")
	        .loginProcessingUrl("/login")
	        .usernameParameter("username")
	        .passwordParameter("password")
	        .defaultSuccessUrl("/welcome")
	        .and().exceptionHandling()
	        .accessDeniedPage("/Autentifikacija/prostupOdbijen")
	        .and()
	        .logout()
	        .logoutSuccessUrl("/");
	}
	
	
	  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		  auth.inMemoryAuthentication(). withUser("emil").
		  password("{noop}123"). roles("admin"); }
	 
}
