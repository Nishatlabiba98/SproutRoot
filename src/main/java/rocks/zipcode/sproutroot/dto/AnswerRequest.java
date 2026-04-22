package rocks.zipcode.sproutroot.dto;

import java.util.UUID;

public class AnswerRequest {

    private UUID sessionId;
    private UUID contentId;
    private UUID childId;
    private String givenAnswer;
    private int responseMs;

    public AnswerRequest() {}

    public UUID getSessionId() { return sessionId; }
    public void setSessionId(UUID sessionId) { this.sessionId = sessionId; }

    public UUID getContentId() { return contentId; }
    public void setContentId(UUID contentId) { this.contentId = contentId; }

    public UUID getChildId() { return childId; }
    public void setChildId(UUID childId) { this.childId = childId; }

    public String getGivenAnswer() { return givenAnswer; }
    public void setGivenAnswer(String givenAnswer) { this.givenAnswer = givenAnswer; }

    public int getResponseMs() { return responseMs; }
    public void setResponseMs(int responseMs) { this.responseMs = responseMs; }
}