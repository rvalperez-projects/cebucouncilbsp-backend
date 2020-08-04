/**
 *
 */
package com.cebucouncilbsp.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author reneir.val.t.perez
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USER = "USER";
	private static final String COUNCIL = "COUNCIL";
	private static final String ADMIN = "ADMIN";

	@Autowired
	private SecurityUserDetailService userDetailService;
	@Autowired
	private SecurityAuthenticationFilter authenticationFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				// set allowed api and role
				// login & logout
				.antMatchers("/auth/**").permitAll()
				// user api
				.antMatchers("/user/sign-up").permitAll().antMatchers("/user/search").hasAnyAuthority(COUNCIL, ADMIN)
				.antMatchers("/user/all").hasAuthority(ADMIN)
				// form api
				.antMatchers("/form/{\\d}}").permitAll().antMatchers("/form/submit").hasAnyAuthority(USER, COUNCIL)
				.antMatchers("/form/search").hasAnyAuthority(COUNCIL, ADMIN).antMatchers("/form/update")
				.hasAnyAuthority(COUNCIL, ADMIN).antMatchers("/form/update-status").hasAnyAuthority(COUNCIL, ADMIN)
				.antMatchers("/form/update").hasAnyAuthority(COUNCIL, ADMIN).antMatchers("/form/all")
				.hasAuthority(ADMIN).anyRequest().authenticated()
				// this disables session creation on Spring Security
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
