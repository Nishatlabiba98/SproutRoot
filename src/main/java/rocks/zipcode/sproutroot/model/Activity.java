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
}