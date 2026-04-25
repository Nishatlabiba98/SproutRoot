package rocks.zipcode.sproutroot.service;

import org.springframework.stereotype.Service;
import rocks.zipcode.sproutroot.model.Activity;
import rocks.zipcode.sproutroot.repository.ActivityRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getActivitiesByGameType(String gameType) {
        List<Activity> activities = activityRepository.findByGameType(gameType);
        Collections.shuffle(activities);
        return activities;
    }

    public Activity getRandomActivity(String gameType) {
        List<Activity> activities = getActivitiesByGameType(gameType);
        if (activities.isEmpty()) return null;
        return activities.get(0);
    }
}