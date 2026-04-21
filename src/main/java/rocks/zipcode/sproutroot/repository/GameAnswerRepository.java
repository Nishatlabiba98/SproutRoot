package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.GameAnswer;

public interface GameAnswerRepository extends JpaRepository<GameAnswer, UUID> {
    List<GameAnswer> findBySessionId(UUID sessionId);
}