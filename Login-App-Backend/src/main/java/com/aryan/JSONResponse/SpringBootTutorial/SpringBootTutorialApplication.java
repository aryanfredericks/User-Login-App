package com.aryan.JSONResponse.SpringBootTutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootTutorialApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialApplication.class, args);
	}
}
