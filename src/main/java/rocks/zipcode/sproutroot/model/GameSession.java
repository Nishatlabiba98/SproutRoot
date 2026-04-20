package rocks.zipcode.sproutroot.model;
import jakarta.persistence.*;
import java.timeLocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id" , nullable = false)
    private Child child;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GameType gameType;

    private int score;

    private int difficultyLevel = 1;

    private boolean completed = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    private LocalDateTime endedAt;

    @oneToMany(mappedBy = "session",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameAnswers> answers;

    public GameSession() {}

    public UUID getId() { return id; }

    public Child getChild() {return child;}
    public void setChild(Child child) { this.child = child; }

    public GameType getGameType() { return gameType; }
    public void setGameType(GameType gameType) { this.gameType = gameType; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(int difficultyLevel) { this.difficultyLevel = difficultyLevel;

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }

    public List<GameAnswers> getAnswers() { return answers; }
    public void setAnswers(List<GameAnswers> answers) { this.answers = answers; }
}