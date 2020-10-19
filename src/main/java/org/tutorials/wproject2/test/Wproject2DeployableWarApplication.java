package org.tutorials.wproject2.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Wproject2DeployableWarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Wproject2DeployableWarApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Wproject2DeployableWarApplication.class);
	}

}
