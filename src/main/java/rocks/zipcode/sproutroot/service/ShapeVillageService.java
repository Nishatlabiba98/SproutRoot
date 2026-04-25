package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShapeVillageService extends AbstractGameService {

    private final Map<UUID, Set<UUID>> sessionUsedIds = new HashMap<>();

    public ShapeVillageService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository) {
        super(gameSessionRepository, gameAnswerRepository, contentRepository,
              mistakePatternRepository, childRepository);
    }

    public GameSession startGame(UUID childId) {
        GameSession session = createSession(childId, GameType.SHAPE_VILLAGE);
        sessionUsedIds.put(session.getId(), new HashSet<>());
        return session;
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> shapes = getContentByTypeAndDifficulty(ContentType.SHAPE, difficulty);

        Set<UUID> used = sessionUsedIds.getOrDefault(sessionId, new HashSet<>());
        List<CurriculumContent> available = shapes.stream()
                .filter(s -> !used.contains(s.getId()))
                .collect(Collectors.toList());
        if (available.isEmpty()) { available = shapes; used.clear(); }

        Collections.shuffle(available);
        CurriculumContent picked = available.get(0);
        used.add(picked.getId());
        sessionUsedIds.put(sessionId, used);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("What shape is this?");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(buildChoices(picked.getValue(), shapes));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}
