package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;

@Service
public class BerryBasketService extends AbstractGameService {

    private final PixabayService pixabayService;

    public BerryBasketService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository,
            PixabayService pixabayService) {
        super(gameSessionRepository, gameAnswerRepository, contentRepository,
              mistakePatternRepository, childRepository);
        this.pixabayService = pixabayService;
    }

    public GameSession startGame(UUID childId) {
        return createSession(childId, GameType.BERRY_BASKET);
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> numbers = getContentByTypeAndDifficulty(ContentType.NUMBER, difficulty);
        Collections.shuffle(numbers);
        CurriculumContent picked = numbers.get(0);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("Tap " + picked.getValue() + " to fill the basket!");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(List.of(picked.getValue()));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setImageUrl(pixabayService.fetchImageUrl(picked.getPixabayKeyword()));
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}