package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.ELOFMilestone;

public interface ELOFMilestoneRepository extends JpaRepository<ELOFMilestone, UUID> {
    List<ELOFMilestone> findByDomain(String domain);
}