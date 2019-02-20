/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.github.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @description : ApplicationConfig
 * @package : com.nts.config
 * @filename : ApplicationConfig.java
 * @author : 최석현
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.nts.dao", "com.nts.service" })
@Import({ DBConfig.class })
public class ApplicationConfig {
	
}