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

/**
 * This class customizes security configurations.
 * It extends WebSecurityConfigurerAdapter, which enables customizing:
 * WebSecurity: creates the Spring Security Filter Chain and
 * HttpSecurity: allows configuring web based security for specific http requests.
 * It also implements WebMvcConfigurer, which customize the Java-based configuration for Spring MVC
 * https://docs.spring.io/spring-security/site/docs/4.2.19.RELEASE/guides/html5/form-javaconfig.html
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    /**
     * This method customizes access to html-pages in the application.
     * Everybody ia allowed to access login and other pages mentioned as
     * parameter in antMatchers().
     * Other requests is limited according to roles.
     * "login" is defined as customized login-page, where username is
     * replaced with brugenavn and password is replaced with kodeord
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/mainCSS.css", "/resources/**", "/bruger/opret_bruger", "/bruger/bruger_side/**").permitAll()
                .anyRequest().hasAnyRole("ORGANIZER", "PARTICIPANT")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                //username is called brugernavn on customized login-page
                .usernameParameter("brugernavn")
                //password is called kodeord on customized login-page
                .passwordParameter("kodeord");
    }

    /**
     * This bean gives BCryptPasswordEncoder, which hashes the password
     * @return
     */
    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

}
