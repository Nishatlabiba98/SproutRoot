package rocks.zipcode.sproutroot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocks.zipcode.sproutroot.model.Activity;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByGameType(String gameType);
}