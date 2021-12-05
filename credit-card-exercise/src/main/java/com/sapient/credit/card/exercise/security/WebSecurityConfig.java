package com.sapient.credit.card.exercise.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(
                request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
                    cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(Arrays.asList("*"));
                    return cors;
                }
        ).and().csrf().disable()// disable csrf validation
                .authorizeRequests()
                .antMatchers().permitAll() // provide urls to be protected along with roles - hasAuthroity
                                            // hasRoles() or hasAnyAuthority()
                .anyRequest().authenticated()
                .and()
                .httpBasic()

        ;
    }
}
