package com.faqtelegrambot.questionservice.bot;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.faqtelegrambot.questionservice.config.BotConfig;
import com.faqtelegrambot.questionservice.service.QuestionService;

@Component
public class QuestionBot extends TelegramLongPollingBot {

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(QuestionBot.class);

    private final QuestionService questionService;
    private final BotConfig botConfig;

    public QuestionBot(QuestionService questionService, BotConfig botConfig) {
        this.questionService = questionService;
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return this.botConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return this.botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Check if the message has text

        try {

            if (update.hasMessage() && update.getMessage().hasText()) {
                String userInput = update.getMessage().getText();
                System.out.println("Received message: " + userInput); // Debugging log
                logger.info("Received message: " + userInput);
                String responseText;

                // Handle the /start command
                if (userInput.equalsIgnoreCase("/start")) {

                    // Send an image
                    SendPhoto photo = new SendPhoto();
                    photo.setChatId(update.getMessage().getChatId().toString());
                    photo.setCaption("Welcome to ABC University!");
                    ClassPathResource resource = new ClassPathResource("static/images/abcuniversity.png");
                    File file = resource.getFile();  // This will get the file from the classpath
                    photo.setPhoto(new InputFile(file));
                    execute(photo); // Send the image

                    responseText = "With a legacy of over 20 years, ABC University has been a renowned institution offering high-quality education in a variety of fields. We are well-regarded for our diploma courses in Data Science, Java, Python, Machine Learning, and other cutting-edge technologies. In addition, we offer comprehensive programs in engineering fields such as Mechanical, Computer Science, and Electrical Engineering, preparing our students for success in the ever-evolving job market." + "\n" + "Admissions are opening soon!" + "\n" + "If you have any questions regarding the admissions process, course offerings, or applications, feel free to ask. Our FAQ Telegram Bot is here to assist you with all the information you need to make the best decision for your academic future." + "\n" + "Wish you good luck!";
                } else {
                    // Default behavior for other text messages
                    responseText = questionService.getBestAnswer(userInput); // Get question by text

                    if (responseText.equals("ERROR")) {
                        responseText = "Sorry, I could not find an answer for your question. Please visit our <a href=\"http://www.abcuniv.edu\">website</a> to post your queries!";

                    }
                }

                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChatId().toString()); // Convert Long to String
                message.setText(responseText);
                message.setParseMode("HTML");

                execute(message); // Sending message
                logger.info("User Message Processed successfully!");
            }
        } catch (TelegramApiException e) {
            // Log for updates that are not valid text messages
            System.out.println("Ignored update: " + update);
        } catch (IOException e) {
            // Log for updates that are not valid text messages
            System.out.println("Ignored update: " + update);
        }
    }
}
