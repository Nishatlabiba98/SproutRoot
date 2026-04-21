package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.ChildMilestone;

public interface ChildMilestoneRepository extends JpaRepository<ChildMilestone, UUID> {
    List<ChildMilestone> findByChildId(UUID childId);
}