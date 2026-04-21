package rocks.zipcode.sproutroot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocks.zipcode.sproutroot.model.ContentType;
import rocks.zipcode.sproutroot.model.CurriculumContent;
import java.util.List;
import java.util.UUID;

public interface CurriculumContentRepository extends JpaRepository<CurriculumContent, UUID> {
    List<CurriculumContent> findByType(ContentType type);
}