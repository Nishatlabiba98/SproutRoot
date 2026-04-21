package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.MistakePattern;

public interface MistakePatternRepository extends JpaRepository<MistakePattern, UUID> {
    List<MistakePattern> findByChildId(UUID childId);
}