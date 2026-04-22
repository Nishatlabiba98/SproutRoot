package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.AnswerRequest;
import rocks.zipcode.sproutroot.dto.AnswerResult;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public abstract class AbstractGameService {

    protected final GameSessionRepository gameSessionRepository;
    protected final GameAnswerRepository gameAnswerRepository;
    protected final CurriculumContentRepository contentRepository;
    protected final MistakePatternRepository mistakePatternRepository;
    protected final ChildRepository childRepository;

    protected static final int QUESTIONS_PER_GAME = 5;
    protected static final int POINTS_PER_CORRECT = 10;

    public AbstractGameService(
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository,
            MistakePatternRepository mistakePatternRepository,
            ChildRepository childRepository) {
        this.gameSessionRepository = gameSessionRepository;
        this.gameAnswerRepository = gameAnswerRepository;
        this.contentRepository = contentRepository;
        this.mistakePatternRepository = mistakePatternRepository;
        this.childRepository = childRepository;
    }

    public abstract GameQuestion buildQuestion(UUID sessionId, int questionNumber);

    public AnswerResult scoreAnswer(AnswerRequest request) {
        GameSession session = gameSessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        CurriculumContent content = contentRepository.findById(request.getContentId())
                .orElseThrow(() -> new RuntimeException("Content not found"));

        boolean correct = isCorrect(request.getGivenAnswer(), content.getValue());

        GameAnswer answer = new GameAnswer();
        answer.setSession(session);
        answer.setContent(content);
        answer.setGivenAnswer(request.getGivenAnswer());
        answer.setCorrect(correct);
        answer.setResponseMs(request.getResponseMs());
        gameAnswerRepository.save(answer);

        int earned = correct ? POINTS_PER_CORRECT : 0;
        session.setScore(session.getScore() + earned);

        List<GameAnswer> answers = gameAnswerRepository.findBySessionId(session.getId());
        boolean complete = answers.size() >= QUESTIONS_PER_GAME;
        if (complete) {
            session.setCompleted(true);
            session.setEndedAt(LocalDateTime.now());
        }
        gameSessionRepository.save(session);

        if (!correct) {
            trackMistake(request, content);
        }

        AnswerResult result = new AnswerResult();
        result.setCorrect(correct);
        result.setScoreEarned(earned);
        result.setTotalScore(session.getScore());
        result.setFeedback(correct ? "Great job!" : "Try again!");
        result.setGameComplete(complete);
        return result;
    }

    protected boolean isCorrect(String given, String expected) {
        if (given == null) return false;
        return given.trim().equalsIgnoreCase(expected.trim());
    }

    protected void trackMistake(AnswerRequest request, CurriculumContent content) {
        Child child = childRepository.findById(request.getChildId()).orElse(null);
        if (child == null) return;

        List<MistakePattern> existing = mistakePatternRepository.findByChildId(request.getChildId());
        Optional<MistakePattern> match = existing.stream()
                .filter(m -> m.getContent().getId().equals(content.getId())
                        && m.getWrongAnswer().equalsIgnoreCase(request.getGivenAnswer()))
                .findFirst();

        if (match.isPresent()) {
            MistakePattern pattern = match.get();
            pattern.setOccurrenceCount(pattern.getOccurrenceCount() + 1);
            pattern.setLastSeenAt(LocalDateTime.now());
            mistakePatternRepository.save(pattern);
        } else {
            MistakePattern pattern = new MistakePattern();
            pattern.setChild(child);
            pattern.setContent(content);
            pattern.setWrongAnswer(request.getGivenAnswer());
            pattern.setOccurrenceCount(1);
            mistakePatternRepository.save(pattern);
        }
    }

    protected GameSession createSession(UUID childId, GameType gameType) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        GameSession session = new GameSession();
        session.setChild(child);
        session.setGameType(gameType);
        session.setDifficultyLevel(getChildDifficultyLevel(childId));
        return gameSessionRepository.save(session);
    }

    protected List<CurriculumContent> getContentByType(ContentType type) {
        return contentRepository.findByType(type);
    }

    protected List<CurriculumContent> getContentByTypeAndDifficulty(ContentType type, int difficultyLevel) {
        List<CurriculumContent> all = contentRepository.findByType(type);
        List<CurriculumContent> filtered = all.stream()
                .filter(c -> c.getDifficultyLevel() <= difficultyLevel)
                .collect(Collectors.toList());
        return filtered.isEmpty() ? all : filtered;
    }

    protected int getChildDifficultyLevel(UUID childId) {
        List<GameSession> sessions = gameSessionRepository.findByChildId(childId);
        if (sessions.isEmpty()) return 1;
        return sessions.stream()
                .mapToInt(GameSession::getDifficultyLevel)
                .max()
                .orElse(1);
    }

    protected List<String> buildChoices(String correct, List<CurriculumContent> pool) {
        List<String> choices = new ArrayList<>();
        choices.add(correct);
        List<CurriculumContent> shuffled = new ArrayList<>(pool);
        Collections.shuffle(shuffled);
        for (CurriculumContent c : shuffled) {
            if (!c.getValue().equalsIgnoreCase(correct) && choices.size() < 4) {
                choices.add(c.getValue());
            }
        }
        Collections.shuffle(choices);
        return choices;
    }
}