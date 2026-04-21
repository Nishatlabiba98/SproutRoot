package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.MistakePattern;
import rocks.zipcode.sproutroot.repository.MistakePatternRepository;

@Service
public class MistakePatternService {

    private final MistakePatternRepository mistakePatternRepository;

    public MistakePatternService(MistakePatternRepository mistakePatternRepository) {
        this.mistakePatternRepository = mistakePatternRepository;
    }

    public List<MistakePattern> getPatternsByChild(UUID childId) {
        return mistakePatternRepository.findByChildId(childId);
    }

    public MistakePattern savePattern(MistakePattern pattern) {
        return mistakePatternRepository.save(pattern);
    }

    public void detect(UUID sessionId) {
        // Phase 1: placeholder — logic added in scoring service
    }
}