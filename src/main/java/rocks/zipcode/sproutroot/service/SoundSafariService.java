package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;

@Service
public class SoundSafariService extends AbstractGameService {

    public SoundSafariService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository) {
        super(gameSessionRepository, gameAnswerRepository, contentRepository,
              mistakePatternRepository, childRepository);
    }

    public GameSession startGame(UUID childId) {
        return createSession(childId, GameType.SOUND_SAFARI);
    }

    @Override
    public GameQuestion buildQuestion(UUID sessionId, int questionNumber) {
        GameSession session = gameSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        int difficulty = getChildDifficultyLevel(session.getChild().getId());
        List<CurriculumContent> letters = getContentByTypeAndDifficulty(ContentType.LETTER, difficulty);
        Collections.shuffle(letters);
        CurriculumContent picked = letters.get(0);

        GameQuestion q = new GameQuestion();
        q.setSessionId(sessionId);
        q.setContentId(picked.getId());
        q.setQuestionText("Which letter makes the \"" + picked.getValue() + "\" sound?");
        q.setCorrectAnswer(picked.getValue());
        q.setChoices(buildChoices(picked.getValue(), letters));
        q.setPixabayKeyword(picked.getPixabayKeyword());
        q.setQuestionNumber(questionNumber);
        q.setTotalQuestions(QUESTIONS_PER_GAME);
        q.setCurrentScore(session.getScore());
        return q;
    }
}