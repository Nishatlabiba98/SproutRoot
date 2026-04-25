package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.model.Activity;
import rocks.zipcode.sproutroot.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/recommend/{gameType}")
    public ResponseEntity<Activity> recommend(@PathVariable String gameType) {
        Activity activity = activityService.getRandomActivity(gameType);
        if (activity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/all/{gameType}")
    public ResponseEntity<List<Activity>> allByGame(@PathVariable String gameType) {
        return ResponseEntity.ok(activityService.getActivitiesByGameType(gameType));
    }
}