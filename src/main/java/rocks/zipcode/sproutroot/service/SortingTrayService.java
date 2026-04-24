package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;

@Service
public class SortingTrayService extends AbstractGameService {

    private final GiphyService giphyService;

    public SortingTrayService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository,
            GiphyService giphyService) {
        super(gameSessionRepository, gameAnswerRepository, contentRepository,
              mistakePatternRepository, childRepository);
        this.giphyService = giphyService;
    }

    public GameSession startGame(UUID childId) {
        return createSession(childId, GameType.SORTING_TRAY);
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> categories = getContentByTypeAndDifficulty(ContentType.CATEGORY, difficulty);
        Collections.shuffle(categories);
        CurriculumContent picked = categories.get(0);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("What do you see? Say the word!");
        q.setCorrectAnswer(picked.getDisplayName());
        q.setChoices(List.of("correct", "wrong"));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setImageUrl(giphyService.fetchGifUrl(picked.getPixabayKeyword()));
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}
