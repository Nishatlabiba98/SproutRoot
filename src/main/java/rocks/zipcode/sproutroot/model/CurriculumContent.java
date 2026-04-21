package rocks.zipcode.sproutroot.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "curriculum_content")
public class CurriculumContent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private ContentType type;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String displayName;

    private int difficultyLevel = 1;

    private String elofDomain;

    private String pixabayKeyword;

    public CurriculumContent() {}

    public UUID getId() { return id; }

    public ContentType getType() { return type; }
    public void setType(ContentType type) { this.type = type; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public int getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(int difficultyLevel) { this.difficultyLevel = difficultyLevel; }

    public String getElofDomain() { return elofDomain; }
    public void setElofDomain(String elofDomain) { this.elofDomain = elofDomain; }

    public String getPixabayKeyword() { return pixabayKeyword; }
    public void setPixabayKeyword(String pixabayKeyword) { this.pixabayKeyword = pixabayKeyword; }
}