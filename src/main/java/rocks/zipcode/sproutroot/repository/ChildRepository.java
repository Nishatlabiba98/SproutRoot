package rocks.zipcode.sproutroot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rocks.zipcode.sproutroot.model.Child;

public interface ChildRepository extends JpaRepository<Child, UUID> {
    List<Child> findByParentId(UUID parentId);
}