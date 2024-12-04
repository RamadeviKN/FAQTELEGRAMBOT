package com.faqtelegrambot.questionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faqtelegrambot.questionservice.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestionText(String questionText);
}
