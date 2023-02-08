package com.openclassrooms.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientApplication {
	private static final Logger logger = LogManager.getLogger("PatientApplication");
	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
		logger.info("Initializing");


	}

}
