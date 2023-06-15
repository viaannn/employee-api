package com.edts.edtstechnicaltest;

import com.edts.edtstechnicaltest.config.AuditorAwareConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EdtsTechnicalTestApplication {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareConfig();
	}

	public static void main(String[] args) {
		SpringApplication.run(EdtsTechnicalTestApplication.class, args);
	}
}

