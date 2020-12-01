package com.padc.demo.core.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//https://www.boraji.com/spring-security-4-custom-login-from-example

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
