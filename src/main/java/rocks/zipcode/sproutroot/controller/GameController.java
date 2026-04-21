package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.model.GameAnswer;
import rocks.zipcode.sproutroot.model.GameSession;
import rocks.zipcode.sproutroot.service.GameAnswerService;
import rocks.zipcode.sproutroot.service.GameSessionService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameSessionService gameSessionService;
    private final GameAnswerService gameAnswerService;

    public GameController(GameSessionService gameSessionService,
                          GameAnswerService gameAnswerService) {
        this.gameSessionService = gameSessionService;
        this.gameAnswerService = gameAnswerService;
    }

    @PostMapping("/session/start")
    public ResponseEntity<GameSession> startSession(@RequestBody GameSession session) {
        return ResponseEntity.ok(gameSessionService.createSession(session));
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<GameSession> getSession(@PathVariable UUID id) {
        return ResponseEntity.ok(gameSessionService.getSessionById(id));
    }

    @GetMapping("/session/child/{childId}")
    public ResponseEntity<List<GameSession>> getSessionsByChild(@PathVariable UUID childId) {
        return ResponseEntity.ok(gameSessionService.getSessionsByChild(childId));
    }

    @PostMapping("/session/end/{id}")
    public ResponseEntity<GameSession> endSession(@PathVariable UUID id,
                                                   @RequestBody GameSession updates) {
        GameSession session = gameSessionService.getSessionById(id);
        session.setScore(updates.getScore());
        session.setCompleted(true);
        session.setEndedAt(java.time.LocalDateTime.now());
        return ResponseEntity.ok(gameSessionService.updateSession(session));
    }

    @PostMapping("/answer")
    public ResponseEntity<GameAnswer> submitAnswer(@RequestBody GameAnswer answer) {
        return ResponseEntity.ok(gameAnswerService.saveAnswer(answer));
    }

    @GetMapping("/answer/session/{sessionId}")
    public ResponseEntity<List<GameAnswer>> getAnswersBySession(@PathVariable UUID sessionId) {
        return ResponseEntity.ok(gameAnswerService.getAnswersBySession(sessionId));
    }
}