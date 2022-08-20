package com.valuable_paper.valuable_paper;

import com.valuable_paper.valuable_paper.entity.ValuablePaper;
import com.valuable_paper.valuable_paper.service.ValuablePaperService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class ValuablePaperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValuablePaperApplication.class, args);
	}





	@Bean
	public CommandLineRunner run(ValuablePaperService valuablePaperService) {
		return args -> {
			valuablePaperService.saveValuablePaper(new ValuablePaper("Сбербанк", 1300));
			valuablePaperService.saveValuablePaper(new ValuablePaper("Тинькофф", 2000));
		};
	}


}
