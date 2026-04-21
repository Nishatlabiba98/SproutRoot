package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.model.ChildMilestone;
import rocks.zipcode.sproutroot.model.MistakePattern;
import rocks.zipcode.sproutroot.service.ChildMilestoneService;
import rocks.zipcode.sproutroot.service.MistakePatternService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final ChildMilestoneService childMilestoneService;
    private final MistakePatternService mistakePatternService;

    public DashboardController(ChildMilestoneService childMilestoneService,
                                MistakePatternService mistakePatternService) {
        this.childMilestoneService = childMilestoneService;
        this.mistakePatternService = mistakePatternService;
    }

    @GetMapping("/milestones/{childId}")
    public ResponseEntity<List<ChildMilestone>> getMilestones(@PathVariable UUID childId) {
        return ResponseEntity.ok(childMilestoneService.getMilestonesByChild(childId));
    }

    @GetMapping("/mistakes/{childId}")
    public ResponseEntity<List<MistakePattern>> getMistakes(@PathVariable UUID childId) {
        return ResponseEntity.ok(mistakePatternService.getPatternsByChild(childId));
    }
}