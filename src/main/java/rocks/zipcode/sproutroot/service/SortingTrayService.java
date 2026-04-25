package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SortingTrayService extends AbstractGameService {

    private final GiphyService giphyService;
    private final Map<UUID, Set<UUID>> sessionUsedIds = new HashMap<>();

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
        GameSession session = createSession(childId, GameType.SORTING_TRAY);
        sessionUsedIds.put(session.getId(), new HashSet<>());
        return session;
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> categories = getContentByTypeAndDifficulty(ContentType.CATEGORY, difficulty);

        Set<UUID> used = sessionUsedIds.getOrDefault(sessionId, new HashSet<>());
        List<CurriculumContent> available = categories.stream()
                .filter(c -> !used.contains(c.getId()))
                .collect(Collectors.toList());
        if (available.isEmpty()) { available = categories; used.clear(); }

        Collections.shuffle(available);
        CurriculumContent picked = available.get(0);
        used.add(picked.getId());
        sessionUsedIds.put(sessionId, used);

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
