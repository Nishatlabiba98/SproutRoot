package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BerryBasketService extends AbstractGameService {

    private final GiphyService giphyService;
    private final Map<UUID, Set<UUID>> sessionUsedIds = new HashMap<>();

    public BerryBasketService(
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
        GameSession session = createSession(childId, GameType.BERRY_BASKET);
        sessionUsedIds.put(session.getId(), new HashSet<>());
        return session;
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> numbers = getContentByTypeAndDifficulty(ContentType.NUMBER, difficulty);

        Set<UUID> used = sessionUsedIds.getOrDefault(sessionId, new HashSet<>());
        List<CurriculumContent> available = numbers.stream()
                .filter(n -> !used.contains(n.getId()))
                .collect(Collectors.toList());
        if (available.isEmpty()) { available = numbers; used.clear(); }

        Collections.shuffle(available);
        CurriculumContent picked = available.get(0);
        used.add(picked.getId());
        sessionUsedIds.put(sessionId, used);

        int target = Integer.parseInt(picked.getValue());
        List<CurriculumContent> allNumbers = getContentByType(ContentType.NUMBER);
        List<String> choices = buildChoices(picked.getValue(), allNumbers);
        String gifKeyword = picked.getPixabayKeyword().split(" ")[0];
        String gifUrl = giphyService.fetchGifUrl(target + " " + gifKeyword);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("How many do you see? Count and pick the number!");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(choices);
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setImageUrl(gifUrl);
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}
