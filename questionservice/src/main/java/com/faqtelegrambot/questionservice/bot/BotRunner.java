package com.faqtelegrambot.questionservice.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.faqtelegrambot.questionservice.config.BotConfig;
import com.faqtelegrambot.questionservice.service.QuestionService;

@Component
public class BotRunner implements CommandLineRunner {

     // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(BotRunner.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private BotConfig botConfig;

    @Override
    public void run(String... args) throws Exception {

        logger.info("BotRunner Started...");

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        // Create an instance of your bot
        QuestionBot bot = new QuestionBot(questionService, botConfig);

        try {
             botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        logger.info("BotRunner ended...");
    }
}
