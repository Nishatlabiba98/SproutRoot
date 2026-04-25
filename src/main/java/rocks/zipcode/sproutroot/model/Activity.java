package rocks.zipcode.sproutroot.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private String materials;

    @Column(nullable = false)
    private String gameType;

    @Column(nullable = false)
    private String elofDomain;

    @Column(nullable = false)
    private int difficultyLevel;

    private String emoji;

    @Column(length = 300)
    private String visualScene;

    @Column(length = 300)
    private String bookRec1;

    @Column(length = 300)
    private String bookRec2;

    @Column(length = 300)
    private String showRec1;

    @Column(length = 300)
    private String showRec2;

    public Activity() {}

    public UUID getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMaterials() { return materials; }
    public void setMaterials(String materials) { this.materials = materials; }

    public String getGameType() { return gameType; }
    public void setGameType(String gameType) { this.gameType = gameType; }

    public String getElofDomain() { return elofDomain; }
    public void setElofDomain(String elofDomain) { this.elofDomain = elofDomain; }

    public int getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(int difficultyLevel) { this.difficultyLevel = difficultyLevel; }

    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }

    public String getVisualScene() { return visualScene; }
    public void setVisualScene(String visualScene) { this.visualScene = visualScene; }

    public String getBookRec1() { return bookRec1; }
    public void setBookRec1(String bookRec1) { this.bookRec1 = bookRec1; }

    public String getBookRec2() { return bookRec2; }
    public void setBookRec2(String bookRec2) { this.bookRec2 = bookRec2; }

    public String getShowRec1() { return showRec1; }
    public void setShowRec1(String showRec1) { this.showRec1 = showRec1; }

    public String getShowRec2() { return showRec2; }
    public void setShowRec2(String showRec2) { this.showRec2 = showRec2; }
}