package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;

@Service
public class ShapeVillageService extends AbstractGameService {

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
        return createSession(childId, GameType.SHAPE_VILLAGE);
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> shapes = getContentByTypeAndDifficulty(ContentType.SHAPE, difficulty);
        Collections.shuffle(shapes);
        CurriculumContent picked = shapes.get(0);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("Find the " + picked.getDisplayName() + " and place it in the scene!");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(buildChoices(picked.getValue(), shapes));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}