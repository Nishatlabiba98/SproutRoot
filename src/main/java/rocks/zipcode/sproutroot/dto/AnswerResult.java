package rocks.zipcode.sproutroot.dto;

public class AnswerResult {

    private boolean correct;
    private int scoreEarned;
    private int totalScore;
    private String feedback;
    private boolean gameComplete;

    public AnswerResult() {}

    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }

    public int getScoreEarned() { return scoreEarned; }
    public void setScoreEarned(int scoreEarned) { this.scoreEarned = scoreEarned; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public boolean isGameComplete() { return gameComplete; }
    public void setGameComplete(boolean gameComplete) { this.gameComplete = gameComplete; }
}