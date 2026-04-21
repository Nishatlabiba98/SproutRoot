package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.model.ContentType;
import rocks.zipcode.sproutroot.model.CurriculumContent;
import rocks.zipcode.sproutroot.repository.CurriculumContentRepository;
import java.util.List;
import java.util.UUID;

@Service
public class CurriculumContentService {

    private final CurriculumContentRepository curriculumContentRepository;

    public CurriculumContentService(CurriculumContentRepository curriculumContentRepository) {
        this.curriculumContentRepository = curriculumContentRepository;
    }

    public List<CurriculumContent> getByType(ContentType type) {
        return curriculumContentRepository.findByType(type);
    }

    public CurriculumContent getById(UUID id) {
        return curriculumContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public List<CurriculumContent> getAll() {
        return curriculumContentRepository.findAll();
    }
}