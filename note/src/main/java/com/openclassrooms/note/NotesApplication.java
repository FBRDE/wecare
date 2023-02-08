package com.openclassrooms.note;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class NotesApplication {
	private static final Logger logger = LogManager.getLogger("WeCareApplication");
	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
		logger.info("Initializing");


	}

}
