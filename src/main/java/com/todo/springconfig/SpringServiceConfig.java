package com.todo.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.todo.persistence.dao"})
public class SpringServiceConfig {
	
}
