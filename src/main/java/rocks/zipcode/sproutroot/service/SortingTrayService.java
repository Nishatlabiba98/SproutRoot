package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;

@Service
public class SortingTrayService extends AbstractGameService {

    public SortingTrayService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository) {
        super(gameSessionRepository, gameAnswerRepository, contentRepository,
              mistakePatternRepository, childRepository);
    }

    public GameSession startGame(UUID childId) {
        return createSession(childId, GameType.SORTING_TRAY);
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<CurriculumContent> categories = getContentByType(ContentType.CATEGORY);
        Collections.shuffle(categories);
        CurriculumContent picked = categories.get(0);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("Sort these into the correct group: " + picked.getDisplayName());
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(buildChoices(picked.getValue(), categories));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}