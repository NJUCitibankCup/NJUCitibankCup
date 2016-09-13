package nju.citicup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
@EnableScheduling
@EnableCaching
public class NjuCitiCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjuCitiCupApplication.class, args);
	}

}
