package nju.citicup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NjuCitiCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjuCitiCupApplication.class, args);
	}

}
