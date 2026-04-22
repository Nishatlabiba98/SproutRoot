package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.dto.AnswerRequest;
import rocks.zipcode.sproutroot.dto.AnswerResult;
import rocks.zipcode.sproutroot.dto.GameQuestion;
import rocks.zipcode.sproutroot.model.GameSession;
import rocks.zipcode.sproutroot.service.SoundSafariService;
import rocks.zipcode.sproutroot.service.BerryBasketService;
import rocks.zipcode.sproutroot.service.ShapeVillageService;
import rocks.zipcode.sproutroot.service.SortingTrayService;
import rocks.zipcode.sproutroot.service.GameSessionService;
import rocks.zipcode.sproutroot.service.GameAnswerService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final SoundSafariService soundSafariService;
    private final BerryBasketService berryBasketService;
    private final ShapeVillageService shapeVillageService;
    private final SortingTrayService sortingTrayService;
    private final GameSessionService gameSessionService;
    private final GameAnswerService gameAnswerService;

    public GameController(
            SoundSafariService soundSafariService,
            BerryBasketService berryBasketService,
            ShapeVillageService shapeVillageService,
            SortingTrayService sortingTrayService,
            GameSessionService gameSessionService,
            GameAnswerService gameAnswerService) {
        this.soundSafariService = soundSafariService;
        this.berryBasketService = berryBasketService;
        this.shapeVillageService = shapeVillageService;
        this.sortingTrayService = sortingTrayService;
        this.gameSessionService = gameSessionService;
        this.gameAnswerService = gameAnswerService;
    }

    // ── SOUND SAFARI ──────────────────────────────────────────────────────────
    @PostMapping("/sound-safari/start/{childId}")
    public ResponseEntity<GameSession> startSoundSafari(@PathVariable UUID childId) {
        return ResponseEntity.ok(soundSafariService.startGame(childId));
    }

    @GetMapping("/sound-safari/question/{sessionId}/{questionNumber}")
    public ResponseEntity<GameQuestion> getSoundSafariQuestion(
            @PathVariable UUID sessionId,
            @PathVariable int questionNumber) {
        return ResponseEntity.ok(soundSafariService.buildQuestion(sessionId, questionNumber));
    }

    // ── BERRY BASKET ──────────────────────────────────────────────────────────
    @PostMapping("/berry-basket/start/{childId}")
    public ResponseEntity<GameSession> startBerryBasket(@PathVariable UUID childId) {
        return ResponseEntity.ok(berryBasketService.startGame(childId));
    }

    @GetMapping("/berry-basket/question/{sessionId}/{questionNumber}")
    public ResponseEntity<GameQuestion> getBerryBasketQuestion(
            @PathVariable UUID sessionId,
            @PathVariable int questionNumber) {
        return ResponseEntity.ok(berryBasketService.buildQuestion(sessionId, questionNumber));
    }

    // ── SHAPE VILLAGE ─────────────────────────────────────────────────────────
    @PostMapping("/shape-village/start/{childId}")
    public ResponseEntity<GameSession> startShapeVillage(@PathVariable UUID childId) {
        return ResponseEntity.ok(shapeVillageService.startGame(childId));
    }

    @GetMapping("/shape-village/question/{sessionId}/{questionNumber}")
    public ResponseEntity<GameQuestion> getShapeVillageQuestion(
            @PathVariable UUID sessionId,
            @PathVariable int questionNumber) {
        return ResponseEntity.ok(shapeVillageService.buildQuestion(sessionId, questionNumber));
    }

    // ── SORTING TRAY ──────────────────────────────────────────────────────────
    @PostMapping("/sorting-tray/start/{childId}")
    public ResponseEntity<GameSession> startSortingTray(@PathVariable UUID childId) {
        return ResponseEntity.ok(sortingTrayService.startGame(childId));
    }

    @GetMapping("/sorting-tray/question/{sessionId}/{questionNumber}")
    public ResponseEntity<GameQuestion> getSortingTrayQuestion(
            @PathVariable UUID sessionId,
            @PathVariable int questionNumber) {
        return ResponseEntity.ok(sortingTrayService.buildQuestion(sessionId, questionNumber));
    }

    // ── SHARED — answer scoring works for all games ───────────────────────────
    @PostMapping("/answer")
    public ResponseEntity<AnswerResult> submitAnswer(@RequestBody AnswerRequest request) {
        return ResponseEntity.ok(soundSafariService.scoreAnswer(request));
    }

    // ── SESSION UTILS ─────────────────────────────────────────────────────────
    @GetMapping("/session/{id}")
    public ResponseEntity<GameSession> getSession(@PathVariable UUID id) {
        return ResponseEntity.ok(gameSessionService.getSessionById(id));
    }

    @GetMapping("/session/child/{childId}")
    public ResponseEntity<List<GameSession>> getSessionsByChild(@PathVariable UUID childId) {
        return ResponseEntity.ok(gameSessionService.getSessionsByChild(childId));
    }
}