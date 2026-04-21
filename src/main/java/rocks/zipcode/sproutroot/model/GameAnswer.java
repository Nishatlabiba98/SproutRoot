package rocks.zipcode.sproutroot.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "game_answer")
public class GameAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private CurriculumContent content;

    private String givenAnswer;
    private boolean correct;
    private int responseMs;

    @Column(nullable = false, updatable = false)
    private LocalDateTime answeredAt = LocalDateTime.now();

    //getters and setters

    public UUID getId() { return id;}

    public GameSession getSession() { return session;}
    public void setSession(GameSession session) { this.session = session;}
    public CurriculumContent getContent() { return content;}
    public void setContent(CurriculumContent content) { this.content = content;}
    public String getGivenAnswer() { return givenAnswer;}
    public void setGivenAnswer(String givenAnswer) { this.givenAnswer = givenAnswer;}
    public boolean isCorrect() { return correct;}
    public void setCorrect(boolean correct) { this.correct = correct;}
    public int getResponseMs() { return responseMs;}
    public void setResponseMs(int responseMs) { this.responseMs = responseMs;}
    public LocalDateTime getAnsweredAt() { return answeredAt;}

}