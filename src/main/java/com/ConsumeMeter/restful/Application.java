package com.ConsumeMeter.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *  LezginAksoy
 */

@ComponentScan("com.ConsumeMeter.restful.controller")
@ComponentScan({"com.ConsumeMeter.restful.service"})
@EntityScan("com.ConsumeMeter.restful.model")
@EnableJpaRepositories("com.ConsumeMeter.restful.repository")
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
