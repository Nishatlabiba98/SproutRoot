package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.GameAnswer;
import rocks.zipcode.sproutroot.repository.GameAnswerRepository;

@Service
public class GameAnswerService {

    private final GameAnswerRepository gameAnswerRepository;

    public GameAnswerService(GameAnswerRepository gameAnswerRepository) {
        this.gameAnswerRepository = gameAnswerRepository;
    }

    public GameAnswer saveAnswer(GameAnswer answer) {
        return gameAnswerRepository.save(answer);
    }

    public List<GameAnswer> getAnswersBySession(UUID sessionId) {
        return gameAnswerRepository.findBySessionId(sessionId);
    }
}