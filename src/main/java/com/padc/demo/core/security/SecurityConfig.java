package com.padc.demo.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://docs.spring.io/spring-security/site/docs/4.2.19.RELEASE/guides/html5/form-javaconfig.html

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/mainCSS.css", "/resources/**", "/webjars/**", "/bruger/opret_bruger", "/bruger/bruger_side/**").permitAll()
                .anyRequest().hasAnyRole("ORGANIZER", "PARTICIPANT")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("brugernavn")
                .passwordParameter("kodeord")
                .permitAll() // Allow access to any URL associate to logout()
                .and()
                .csrf().disable(); // Disable CSRF support
    }

    @Bean
    //@SuppressWarnings("deprecation")
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

}