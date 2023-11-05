package com.easytravel.userManagementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@EntityScan("com.easytravel.userManagementService.model") // since its in a different package than springbootApp, we need it so that jpa finds the entity to create the tables.
public class UserManagementServiceApplication {
	private static final Logger logger= LoggerFactory.getLogger(UserManagementServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
		logger.info("application started successfully");



	}

}

