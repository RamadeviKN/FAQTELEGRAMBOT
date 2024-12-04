package com.faqtelegrambot.questionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.faqtelegrambot.questionservice.config.BotConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "FAQ API", version = "v1"))
@SpringBootApplication
@EnableConfigurationProperties(BotConfig.class)
public class QuestionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionserviceApplication.class, args);
	}

}
