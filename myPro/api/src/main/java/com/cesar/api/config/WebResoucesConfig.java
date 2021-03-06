package com.cesar.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebResoucesConfig extends WebMvcConfigurationSupport
{

    @Override
    protected void addResourceHandlers(
    	ResourceHandlerRegistry registry
    ) {
        registry.addResourceHandler("/**").
                   addResourceLocations("classpath:/static/");
    }
}