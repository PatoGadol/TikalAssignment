package com.tikal.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.IOException;

/**
 * Created by Sopher on 01/03/2017.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.print("Authenticating...");
        http.authorizeRequests().antMatchers("/").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().authoritiesByUsernameQuery()
        System.err.print("Authenticating...");
        auth.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
    }*/


}
