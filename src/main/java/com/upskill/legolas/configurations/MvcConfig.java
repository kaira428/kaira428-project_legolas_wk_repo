package com.upskill.legolas.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurationSupport{

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {         
//		registry.addResourceHandler("/**")
//				.addResourceLocations("classpath:/static/");
//	}
		
//	public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/fonts/**", "/images/**", "/css/**");
//    }
}
