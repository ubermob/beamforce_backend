package ru.beamforce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private PasswordEncoder passwordEncoder;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
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
				.logout().permitAll().logoutSuccessUrl("/").deleteCookies("JSESSIONID")
				.and()
				// Remember me
				.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(604_800); // 7 day = 604 800 seconds
	}
}