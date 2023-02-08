package com.openclassrooms.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.report")
public class ReportApplication {
	private static final Logger logger = LogManager.getLogger("ReportApplication");
	public static void main(String[] args) {
		SpringApplication.run(ReportApplication.class, args);
		logger.info("Initializing");


	}

}
