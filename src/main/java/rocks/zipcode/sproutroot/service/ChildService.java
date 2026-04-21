package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.Child;
import rocks.zipcode.sproutroot.repository.ChildRepository;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<Child> getChildrenByParent(UUID parentId) {
        return childRepository.findByParentId(parentId);
    }

    public Child getChildById(UUID id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found"));
    }

    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    public void deleteChild(UUID id) {
        childRepository.deleteById(id);
    }
}