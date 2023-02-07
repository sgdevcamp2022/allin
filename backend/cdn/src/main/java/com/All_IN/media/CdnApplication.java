package com.All_IN.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CdnApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdnApplication.class, args);
	}

}
