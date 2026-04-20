package rocks.zipcode.sproutroot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id" ,  nullable = false)
    private Parent parent;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int ageMonths;

    private String avatarColor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSession> gameSessions;

    public Child () {}
    public UUID getId() { return id;}

    public Parent getParent() { return parent; }
    public void setParent(Parent parent) { this.parent = parent; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAgeMonths() { return ageMonths; }
    public void setAgeMonths(int ageMonths) { this.ageMonths = ageMonths; }

    public String getAvatarColor() { return avatarColor; }
    public void setAvatarColor(String avatarColor) { this.avatarColor = avatarColor; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<GameSession> getGameSessions() { return gameSessions; }
    public void setGameSessions(List<GameSession> gameSessions) { this.gameSessions = gameSessions; }
}
