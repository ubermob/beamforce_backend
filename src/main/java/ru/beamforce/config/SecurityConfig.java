package ru.beamforce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// https://habr.com/ru/post/482552/
		http.csrf().disable().authorizeRequests()
				// All
				.antMatchers("/", "/reg/**", "/help/**", "/about", "/test/**").permitAll()
				.antMatchers("/style.css").permitAll()
				// Admin
				.antMatchers("/admin/**").hasRole("ADMIN")
				// User
				.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated()
				.and()
				// Login
				.formLogin().defaultSuccessUrl("/user").permitAll()
				.and()
				// Logout
				.logout().permitAll().logoutSuccessUrl("/");
	}
}