package rocks.zipcode.sproutroot.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "child_milestone")
public class ChildMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", nullable = false)
    private ELOFMilestone milestone;

    private int masteryScore;

    private LocalDateTime achievedAt = LocalDateTime.now();

    public ChildMilestone() {}

    public UUID getId() { return id; }

    public Child getChild() { return child; }
    public void setChild(Child child) { this.child = child; }

    public ELOFMilestone getMilestone() { return milestone; }
    public void setMilestone(ELOFMilestone milestone) { this.milestone = milestone; }

    public int getMasteryScore() { return masteryScore; }
    public void setMasteryScore(int masteryScore) { this.masteryScore = masteryScore; }

    public LocalDateTime getAchievedAt() { return achievedAt; }
    public void setAchievedAt(LocalDateTime achievedAt) { this.achievedAt = achievedAt; }
}