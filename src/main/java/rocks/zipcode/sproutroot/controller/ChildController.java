package rocks.zipcode.sproutroot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.sproutroot.model.Child;
import rocks.zipcode.sproutroot.service.ChildService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<Child>> getChildrenByParent(@PathVariable UUID parentId) {
        return ResponseEntity.ok(childService.getChildrenByParent(parentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable UUID id) {
        return ResponseEntity.ok(childService.getChildById(id));
    }

    @PostMapping
    public ResponseEntity<Child> createChild(@RequestBody Child child) {
        return ResponseEntity.ok(childService.createChild(child));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@PathVariable UUID id) {
        childService.deleteChild(id);
        return ResponseEntity.noContent().build();
    }
}