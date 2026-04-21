package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.GameSession;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
    List<GameSession> findByChildId(UUID childId);
}