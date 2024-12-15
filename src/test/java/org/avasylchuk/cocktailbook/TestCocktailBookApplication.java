package org.avasylchuk.cocktailbook;

import org.springframework.boot.SpringApplication;

public class TestCocktailBookApplication {

	public static void main(String[] args) {
		SpringApplication.from(CocktailBookApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
