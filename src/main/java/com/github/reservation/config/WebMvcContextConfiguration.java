/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.reservation.interceptor.SessionInterceptor;

/**
 *
 * @description : WebMvcContextConfiguration
 * @package : com.nts.config
 * @filename : WebMvcContextConfiguration.java
 * @author : 최석현
 * @method : void
 *         configureDefaultServletHandling(DefaultServletHandlerConfigurer
 *         configurer)
 * @method : void addViewControllers(final ViewControllerRegistry registry)
 * @method : InternalResourceViewResolver getInternalResourceViewResolver()
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.nts.controller", "com.nts.intercepter" })
@Import(ControllerExceptionHandlerConfig.class)
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	private static final int YEAR_SECONDS = 31556926;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/img_map/**").addResourceLocations("/img_map/").setCachePeriod(YEAR_SECONDS);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(YEAR_SECONDS);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
        registry.addViewController("/detail").setViewName("detail");
        registry.addViewController("/review").setViewName("review");
        registry.addViewController("/reservation").setViewName("reserve");
        registry.addViewController("/bookinglogin").setViewName("bookinglogin");
        registry.addViewController("/myreservation").setViewName("myreservation");
        registry.addViewController("/error/invalid-parameter-error"); 
        registry.addViewController("/error/empty-resultset-error");   
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new SessionInterceptor())
    								.addPathPatterns("/api/reservations")
    								.excludePathPatterns("/api/reservations/reservation");
	}
}