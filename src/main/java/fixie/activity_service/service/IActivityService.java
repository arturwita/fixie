package fixie.activity_service.service;

import fixie.activity_service.dto.ActivityDTO;
import fixie.activity_service.dto.SingleActivityDTO;
import fixie.activity_service.entity.Activity;
import fixie.activity_service.exception.ActivityNotFoundException;
import fixie.activity_service.exception.UnknownStatusException;

import java.util.List;

public interface IActivityService {

    List<Activity> getActivities();

    List<Activity> createActivities(ActivityDTO activityDTO);

    Activity updateActivity(Long id, SingleActivityDTO activityDTO)
            throws ActivityNotFoundException, UnknownStatusException;

    List<Activity> getWorkerActivities(Long id);
}
