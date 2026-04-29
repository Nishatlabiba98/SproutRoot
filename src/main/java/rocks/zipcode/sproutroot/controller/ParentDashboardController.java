package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.dto.ParentDashboard;
import rocks.zipcode.sproutroot.service.ParentDashboardService;

import java.util.UUID;

@RestController
@RequestMapping("/api/parent")
public class ParentDashboardController {

    private final ParentDashboardService dashboardService;

    public ParentDashboardController(ParentDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard/{childId}")
    public ResponseEntity<ParentDashboard> getDashboard(@PathVariable UUID childId) {
        return ResponseEntity.ok(dashboardService.getDashboard(childId));
    }
}
