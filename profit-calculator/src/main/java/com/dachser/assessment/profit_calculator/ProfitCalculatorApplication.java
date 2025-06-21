package com.dachser.assessment.profit_calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProfitCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfitCalculatorApplication.class, args);
	}

}
