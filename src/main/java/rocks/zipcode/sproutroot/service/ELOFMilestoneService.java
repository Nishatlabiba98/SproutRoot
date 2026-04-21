package rocks.zipcode.sproutroot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rocks.zipcode.sproutroot.model.ELOFMilestone;
import rocks.zipcode.sproutroot.repository.ELOFMilestoneRepository;

@Service
public class ELOFMilestoneService {

    private final ELOFMilestoneRepository elofMilestoneRepository;

    public ELOFMilestoneService(ELOFMilestoneRepository elofMilestoneRepository) {
        this.elofMilestoneRepository = elofMilestoneRepository;
    }

    public List<ELOFMilestone> getAll() {
        return elofMilestoneRepository.findAll();
    }

    public List<ELOFMilestone> getByDomain(String domain) {
        return elofMilestoneRepository.findByDomain(domain);
    }

    public ELOFMilestone getById(UUID id) {
        return elofMilestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
    }
}