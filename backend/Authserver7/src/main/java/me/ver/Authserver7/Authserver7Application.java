package me.ver.Authserver7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Authserver7Application {

	public static void main(String[] args) {
		SpringApplication.run(Authserver7Application.class, args);
	}

}
