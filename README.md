# FAQTelegramBot

**FAQTelegramBot** is a Telegram bot application designed to assist students and new joiners with admission-related queries for ABC University. The bot allows users to ask questions regarding admissions, and the bot will provide FAQ answers stored in a MySQL database. If a user's query is not found, the bot will suggest referring to the university's website to post the query.

## Tech Stack
- **MySQL**: For storing FAQ questions and answers.
- **Spring Boot**: QuestionService app to handle user queries and fetch answers from the MySQL database.
  - **Java 17**
  - **Spring Boot 3.4.0**
  - **RestTemplate**
  - **JPA (Jakarta Persistence)**
  - **Telegram Bots**: To interface with Telegram API and interact with users.
  - **SLF4J**: For logging.
- **nlpservice_backend (Python Flask)**: A Python-based backend service that uses `spacy` and `en_core_web_md` model to analyze the similarity between user queries and FAQs, returning the most relevant FAQ.
  - **Python Flask** with **Spacy** for NLP.
- **Docker**: The backend is containerized using Docker for easy deployment.
- **Telegram API**: The bot communicates with users over Telegram to provide information.

## Setup and Installation

### 1. Clone the Repository
Clone the repository to your local machine:

```bash
git clone https://github.com/RamadeviKN/FAQTelegramBot.git
cd FAQTelegramBot

QuestionService Setup (Spring Boot)
The QuestionService application is a Spring Boot-based backend service that connects with the Telegram bot, fetches user queries, and fetches the relevant FAQ answers from MySQL.

Navigate to questionservice directory:

cd questionservice

Build and Run the Spring Boot Application:

mvn clean install
mvn spring-boot:run


nlpservice_backend Setup (Python Flask)
The nlpservice_backend is a Flask app that analyzes the similarity between the user's query and stored FAQ questions using spacy for NLP.

Navigate to nlpservice_backend directory:

cd nlpservice_backend

Build Docker Image:

docker build -t nlpservice_backend .

Run Docker Container:

docker run -d -p 5000:5000 nlpservice_backend

This will run the backend service inside a container, exposing port 5000.

Testing the Application
Once the backend and database are up, you can start the Telegram bot and test it by sending queries related to ABC University admissions.

Open Telegram and search for your bot.
Start the bot and ask questions like:
"How can I apply for a course at ABC University?"
"What are the admission requirements?"
"What is the application deadline?"
The bot will respond with relevant FAQ answers. If the answer is not found, it will prompt the user to visit the university website.

Git Commands for Cloning, Committing, and Pushing
Cloning the Repository
Clone the repository:
  git clone https://github.com/RamadeviKN/FAQTelegramBot.git
Navigate to the project directory:
  cd FAQTelegramBot
Committing Changes
Add files to staging:
  git add .
Commit your changes:
  git commit -m "Initial commit for FAQTelegramBot"
Push changes to GitHub:
  git push -u origin main

Future Enhancements
Docker Compose: Dockerize the entire app using Docker Compose for easier orchestration of all services (MySQL, Spring Boot, Flask, and Telegram bot).
Student Registrations: Implement student registration through the Telegram bot, where users can register their details and track their application status.

Special Thanks
Special thanks to the "Java Programming with AI and Bot Development" workshop hosted by Dr. Tetiana Polhul for providing the foundational knowledge to build this project.





