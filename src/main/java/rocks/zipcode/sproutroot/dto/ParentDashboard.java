package rocks.zipcode.sproutroot.dto;

import java.util.*;

public class ParentDashboard {
    private String childName;
    private int childAgeMonths;
    private int difficultyLevel;
    private int totalSessions;
    private int totalCorrect;
    private int totalWrong;
    private Map<String, GameStat> gameStats;
    private List<StruggleItem> topStruggles;
    private Map<String, Integer> elofDomains;
    private List<SessionSummary> recentSessions;

    public static class GameStat {
        public String gameType;
        public int correct;
        public int wrong;
        public int sessions;
        public int pct;
    }

    public static class StruggleItem {
        public String contentValue;
        public String contentType;
        public int wrongCount;
        public String emoji;
    }

    public static class SessionSummary {
        public String gameType;
        public int score;
        public String date;
    }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }
    public int getChildAgeMonths() { return childAgeMonths; }
    public void setChildAgeMonths(int childAgeMonths) { this.childAgeMonths = childAgeMonths; }
    public int getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(int difficultyLevel) { this.difficultyLevel = difficultyLevel; }
    public int getTotalSessions() { return totalSessions; }
    public void setTotalSessions(int totalSessions) { this.totalSessions = totalSessions; }
    public int getTotalCorrect() { return totalCorrect; }
    public void setTotalCorrect(int totalCorrect) { this.totalCorrect = totalCorrect; }
    public int getTotalWrong() { return totalWrong; }
    public void setTotalWrong(int totalWrong) { this.totalWrong = totalWrong; }
    public Map<String, GameStat> getGameStats() { return gameStats; }
    public void setGameStats(Map<String, GameStat> gameStats) { this.gameStats = gameStats; }
    public List<StruggleItem> getTopStruggles() { return topStruggles; }
    public void setTopStruggles(List<StruggleItem> topStruggles) { this.topStruggles = topStruggles; }
    public Map<String, Integer> getElofDomains() { return elofDomains; }
    public void setElofDomains(Map<String, Integer> elofDomains) { this.elofDomains = elofDomains; }
    public List<SessionSummary> getRecentSessions() { return recentSessions; }
    public void setRecentSessions(List<SessionSummary> recentSessions) { this.recentSessions = recentSessions; }
}
