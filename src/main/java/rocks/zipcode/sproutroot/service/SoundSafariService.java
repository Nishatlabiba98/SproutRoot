package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SoundSafariService extends AbstractGameService {

    private final GiphyService giphyService;
    private final Map<UUID, Set<UUID>> sessionUsedIds = new HashMap<>();

    public SoundSafariService(
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
        GameSession session = createSession(childId, GameType.SOUND_SAFARI);
        sessionUsedIds.put(session.getId(), new HashSet<>());
        return session;
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> letters = getContentByTypeAndDifficulty(ContentType.LETTER, difficulty);

        Set<UUID> used = sessionUsedIds.getOrDefault(sessionId, new HashSet<>());
        List<CurriculumContent> available = letters.stream()
                .filter(l -> !used.contains(l.getId()))
                .collect(Collectors.toList());
        if (available.isEmpty()) { available = letters; used.clear(); }

        Collections.shuffle(available);
        CurriculumContent picked = available.get(0);
        used.add(picked.getId());
        sessionUsedIds.put(sessionId, used);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("What sound does this letter make?");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(buildChoices(picked.getValue(), letters));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setImageUrl(giphyService.fetchGifUrl(picked.getPixabayKeyword()));
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}
