package com.faqtelegrambot.questionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.faqtelegrambot.questionservice.service.QuestionService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "FAQ API", version = "v1"))
@RestController
@RequestMapping("/questions")
public class QuestionController {

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

   // Endpoint to handle user question
    @GetMapping("/getAnswer")
    public ResponseEntity<String> getAnswer(@RequestParam String questionText) {
        logger.info("Inside QuestionController - getAnswer()");
        String question = questionService.getBestAnswer(questionText);
        if (question != null) {
            logger.info("Answers fetched from DB successfully!");
            return ResponseEntity.ok(question);  // 200 OK response with question and answer
        } else {
            logger.error("Answers not found in DB!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 Not Found if question doesn't exist
    }
}

}
