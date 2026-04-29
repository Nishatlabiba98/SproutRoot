package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.dto.ParentDashboard;
import rocks.zipcode.sproutroot.model.*;
import rocks.zipcode.sproutroot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParentDashboardService {

    private final ChildRepository childRepository;
    private final GameSessionRepository gameSessionRepository;
    private final GameAnswerRepository gameAnswerRepository;
    private final CurriculumContentRepository contentRepository;

    public ParentDashboardService(
            ChildRepository childRepository,
            GameSessionRepository gameSessionRepository,
            GameAnswerRepository gameAnswerRepository,
            CurriculumContentRepository contentRepository) {
        this.childRepository = childRepository;
        this.gameSessionRepository = gameSessionRepository;
        this.gameAnswerRepository = gameAnswerRepository;
        this.contentRepository = contentRepository;
    }

    public ParentDashboard getDashboard(UUID childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        List<GameSession> sessions = gameSessionRepository.findByChildId(childId);

        // collect all answers across all sessions
        List<GameAnswer> answers = new ArrayList<>();
        for (GameSession session : sessions) {
            answers.addAll(gameAnswerRepository.findBySessionId(session.getId()));
        }

        ParentDashboard dashboard = new ParentDashboard();
        dashboard.setChildName(child.getName());
        dashboard.setChildAgeMonths(child.getAgeMonths());
        dashboard.setDifficultyLevel(1);
        dashboard.setTotalSessions(sessions.size());

        long correct = answers.stream().filter(GameAnswer::isCorrect).count();
        long wrong = answers.stream().filter(a -> !a.isCorrect()).count();
        dashboard.setTotalCorrect((int) correct);
        dashboard.setTotalWrong((int) wrong);

        // Per-game stats
        Map<String, ParentDashboard.GameStat> gameStats = new LinkedHashMap<>();
        for (GameType type : GameType.values()) {
            String key = type.name();
            List<GameAnswer> gameAnswers = answers.stream()
                    .filter(a -> a.getSession().getGameType() == type)
                    .collect(Collectors.toList());
            long c = gameAnswers.stream().filter(GameAnswer::isCorrect).count();
            long w = gameAnswers.stream().filter(a -> !a.isCorrect()).count();
            long s = sessions.stream().filter(sess -> sess.getGameType() == type).count();
            ParentDashboard.GameStat stat = new ParentDashboard.GameStat();
            stat.gameType = key;
            stat.correct = (int) c;
            stat.wrong = (int) w;
            stat.sessions = (int) s;
            stat.pct = (c + w > 0) ? (int) (c * 100 / (c + w)) : 0;
            gameStats.put(key, stat);
        }
        dashboard.setGameStats(gameStats);

        // Top struggles
        Map<UUID, Long> wrongCounts = answers.stream()
                .filter(a -> !a.isCorrect())
                .collect(Collectors.groupingBy(a -> a.getContent().getId(), Collectors.counting()));

        List<ParentDashboard.StruggleItem> struggles = wrongCounts.entrySet().stream()
                .sorted(Map.Entry.<UUID, Long>comparingByValue().reversed())
                .limit(5)
                .map(e -> {
                    ParentDashboard.StruggleItem item = new ParentDashboard.StruggleItem();
                    contentRepository.findById(e.getKey()).ifPresent(c -> {
                        item.contentValue = c.getDisplayName();
                        item.contentType = c.getType().name();
                        item.emoji = c.getPixabayKeyword();
                    });
                    item.wrongCount = e.getValue().intValue();
                    return item;
                })
                .collect(Collectors.toList());
        dashboard.setTopStruggles(struggles);

        // ELOF domains
        Map<String, Integer> elof = new LinkedHashMap<>();
        elof.put("Language & Literacy", (int) answers.stream()
                .filter(a -> a.isCorrect() && "Language and Literacy".equals(a.getContent().getElofDomain()))
                .count());
        elof.put("Cognition", (int) answers.stream()
                .filter(a -> a.isCorrect() && "Cognition".equals(a.getContent().getElofDomain()))
                .count());
        dashboard.setElofDomains(elof);

        // Recent sessions
        List<ParentDashboard.SessionSummary> recent = sessions.stream()
                .sorted(Comparator.comparing(GameSession::getStartedAt).reversed())
                .limit(10)
                .map(s -> {
                    ParentDashboard.SessionSummary sum = new ParentDashboard.SessionSummary();
                    sum.gameType = s.getGameType().name();
                    sum.score = s.getScore();
                    sum.date = s.getStartedAt().toString().substring(0, 10);
                    return sum;
                })
                .collect(Collectors.toList());
        dashboard.setRecentSessions(recent);

        return dashboard;
    }
}
