package rocks.zipcode.sproutroot.dto;

import java.util.List;
import java.util.UUID;

public class GameQuestion {

    private UUID sessionId;
    private UUID contentId;
    private String questionText;
    private String correctAnswer;
    private List<String> choices;
    private String pixabayKeyword;
    private int questionNumber;
    private int totalQuestions;
    private int currentScore;

    public GameQuestion() {}

    public UUID getSessionId() { return sessionId; }
    public void setSessionId(UUID sessionId) { this.sessionId = sessionId; }

    public UUID getContentId() { return contentId; }
    public void setContentId(UUID contentId) { this.contentId = contentId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public List<String> getChoices() { return choices; }
    public void setChoices(List<String> choices) { this.choices = choices; }

    public String getPixabayKeyword() { return pixabayKeyword; }
    public void setPixabayKeyword(String pixabayKeyword) { this.pixabayKeyword = pixabayKeyword; }

    public int getQuestionNumber() { return questionNumber; }
    public void setQuestionNumber(int questionNumber) { this.questionNumber = questionNumber; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public int getCurrentScore() { return currentScore; }
    public void setCurrentScore(int currentScore) { this.currentScore = currentScore; }
}