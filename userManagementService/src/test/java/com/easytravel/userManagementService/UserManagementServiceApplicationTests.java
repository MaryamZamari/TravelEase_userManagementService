package com.easytravel.userManagementService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserManagementServiceApplicationTests {

	@Test
	void contextLoads() {
		//assertEquals("5.1.10.RELEASE", SpringVersion.getVersion());   //actual: 6.0.13
		//assertEquals("1.8.0_191", System.getProperty("java.version")); //Actual: 20.0.1
		//assertEquals("1.8", JavaVersion.getJavaVersion().toString()); //Actual: 20


	}

}
