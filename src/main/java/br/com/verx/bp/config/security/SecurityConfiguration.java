package br.com.verx.bp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationService authenticationService;

	// authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/status").permitAll()
		.antMatchers(HttpMethod.GET, "/configdb").permitAll()
		.antMatchers(HttpMethod.GET, "/areas").permitAll()
		.antMatchers(HttpMethod.GET, "/areas/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
	}
	
	// static src configs
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
	public static void main(String[] args) {
		System.err.println(new BCryptPasswordEncoder().encode("verx123"));
	}

}
