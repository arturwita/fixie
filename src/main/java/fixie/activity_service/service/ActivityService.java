package fixie.activity_service.service;

import fixie.activity_service.dto.ActivityDTO;
import fixie.activity_service.dto.SingleActivityDTO;
import fixie.activity_service.entity.Activity;
import fixie.activity_service.entity.Status;
import fixie.activity_service.exception.ActivityNotFoundException;
import fixie.activity_service.exception.UnknownStatusException;
import fixie.activity_service.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements IActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> createActivities(ActivityDTO activityDTO) {
        List<Activity> activities = new ArrayList<>();

        for(SingleActivityDTO singleActivity : activityDTO.activities) {
            Activity activity = Activity.builder()
                    .orderId(activityDTO.orderId)
                    .partId(singleActivity.partId)
                    .activityType(singleActivity.activityType)
                    .status(Status.OPEN)
                    .build();

            activities.add(activity);
        }
        activityRepository.saveAll(activities);
        return activities;
    }

    @Override
    public Activity updateActivity(Long id, SingleActivityDTO activityDTO)
            throws ActivityNotFoundException, UnknownStatusException {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        if(!activityOptional.isPresent()) throw new ActivityNotFoundException();
        Activity activity = activityOptional.get();

        List<String> statuses = Status.getPossibleStatuses();
        if (!statuses.contains(activityDTO.status)) {
            throw new UnknownStatusException();
        }

        // if it didn't come in DTO - don't update that field
        String newStatus = activityDTO.status != null ? activityDTO.status : activity.getStatus();
        Long newWorkerId = activityDTO.workerId != null ? activityDTO.workerId : activity.getWorkerId();

        activity.setStatus(newStatus);
        activity.setWorkerId(newWorkerId);

        activityRepository.save(activity);

        return activity;
    }

    @Override
    public List<Activity> getWorkerActivities(Long id) {
        return activityRepository.findByWorkerId(id);
    }
}
