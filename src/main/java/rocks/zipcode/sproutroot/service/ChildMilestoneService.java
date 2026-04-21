package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.ChildMilestone;
import rocks.zipcode.sproutroot.repository.ChildMilestoneRepository;

@Service
public class ChildMilestoneService {

    private final ChildMilestoneRepository childMilestoneRepository;

    public ChildMilestoneService(ChildMilestoneRepository childMilestoneRepository) {
        this.childMilestoneRepository = childMilestoneRepository;
    }

    public List<ChildMilestone> getMilestonesByChild(UUID childId) {
        return childMilestoneRepository.findByChildId(childId);
    }

    public ChildMilestone saveMilestone(ChildMilestone milestone) {
        return childMilestoneRepository.save(milestone);
    }
}