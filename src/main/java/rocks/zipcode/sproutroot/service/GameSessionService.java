package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.GameSession;
import rocks.zipcode.sproutroot.repository.GameSessionRepository;

@Service
public class GameSessionService {

    private final GameSessionRepository gameSessionRepository;

    public GameSessionService(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    public GameSession createSession(GameSession session) {
        return gameSessionRepository.save(session);
    }

    public GameSession getSessionById(UUID id) {
        return gameSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public List<GameSession> getSessionsByChild(UUID childId) {
        return gameSessionRepository.findByChildId(childId);
    }

    public GameSession updateSession(GameSession session) {
        return gameSessionRepository.save(session);
    }
}