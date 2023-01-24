package com.udg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.udg.security.JWTAuthorizationFilter;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class UniversityOfGuadalajaraBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(UniversityOfGuadalajaraBackEndApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/signin").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
				.antMatchers(HttpMethod.POST, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
				//Quitas el permiso graphql, sólo lo habilité para poder ver la consola de graphiql
				.antMatchers(HttpMethod.GET, "/graphiql/**", "/subscriptions", "/vendor/**", "/graphql").permitAll()
				.antMatchers(HttpMethod.POST, "/graphiql/**", "/subscriptions", "/vendor/**", "/graphql").permitAll()
				.anyRequest().authenticated();
		}
	}
}