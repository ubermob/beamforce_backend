package ru.beamforce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Andrey Korneychuk on 02-Feb-22
 * @version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// https://habr.com/ru/post/482552/
		http.csrf().disable().authorizeRequests()
				// All
				.antMatchers("/", "/reg/**", "/help/**", "/about", "/test/**", "/example").permitAll()
				.antMatchers("/style.css", "/favicon.png", "/example.js").permitAll()
				.antMatchers("/free-api/**").permitAll()
				// Admin
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				// User
				// hasRole() do not work. Replaced to hasAnyAuthority()
				.antMatchers("/user/**", "/api/**").hasAnyAuthority("ADMIN", "USER")
				.anyRequest().authenticated()
				.and()
				// Login (custom)
				.formLogin().loginPage("/login").defaultSuccessUrl("/user").permitAll()
				.and()
				// Logout
				.logout().permitAll().logoutSuccessUrl("/").deleteCookies("JSESSIONID")
				.and()
				// Remember me
				.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(604_800); // 7 day = 604 800 seconds
	}
}