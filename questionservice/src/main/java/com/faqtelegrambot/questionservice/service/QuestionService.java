package com.faqtelegrambot.questionservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faqtelegrambot.questionservice.model.Question;
import com.faqtelegrambot.questionservice.repository.QuestionRepository;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private NlpService nlpService;

    public String getBestAnswer(String userQuery) {
        List<Question> questions = questionRepository.findAll(); // Fetch DB data
        // Convert the list of Question objects into a list of maps
        List<Map<String, String>> questionMaps = questions.stream()
            .map(question -> Map.of(
                    "question_text", question.getQuestionText(),
                    "answer", question.getAnswer()
            ))
            .collect(Collectors.toList());
            System.out.println("Questions fetched from DB and passing to NLPService. Total Questions : " + questionMaps.size());
        return nlpService.getBestAnswer(userQuery, questionMaps);   // Semantic matching
    }
}
