package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.model.ContentType;
import rocks.zipcode.sproutroot.model.CurriculumContent;
import rocks.zipcode.sproutroot.service.CurriculumContentService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/curriculum")
public class CurriculumController {

    private final CurriculumContentService curriculumContentService;

    public CurriculumController(CurriculumContentService curriculumContentService) {
        this.curriculumContentService = curriculumContentService;
    }

    @GetMapping
    public ResponseEntity<List<CurriculumContent>> getAll() {
        return ResponseEntity.ok(curriculumContentService.getAll());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CurriculumContent>> getByType(@PathVariable ContentType type) {
        return ResponseEntity.ok(curriculumContentService.getByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumContent> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(curriculumContentService.getById(id));
    }
}