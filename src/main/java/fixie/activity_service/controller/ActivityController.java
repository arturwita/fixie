package fixie.activity_service.controller;

import fixie.activity_service.dto.ActivityDTO;
import fixie.activity_service.dto.SingleActivityDTO;
import fixie.activity_service.entity.Activity;
import fixie.activity_service.service.ActivityService;
import fixie.common.Roles;
import fixie.common.service.RoleService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ActivityController {
    private final ActivityService activityService;
    private final RoleService roleService;
    private final String[] managingRoles = {Roles.WORKER, Roles.MANAGER, Roles.ADMIN};

    public ActivityController(ActivityService activityService, RoleService roleService) {
        this.activityService = activityService;
        this.roleService = roleService;
    }

    @PostMapping("/activities")
    public List<Activity> createActivities(@RequestBody ActivityDTO activityDTO) {
        // TODO: check if token's user ID matches the Request's user ID (call RequestService to obtain that info, or make HEAD endpoint there which will return 4xx error if not)
        return activityService.createActivities(activityDTO);
    }

    @SneakyThrows
    @GetMapping("/activities")
    public List<Activity> getActivities(@RequestHeader String token) {
        roleService.checkTokenRole(token, managingRoles);
        return activityService.getActivities();
    }

    @SneakyThrows
    @GetMapping("/activities/worker/{id}")
    public List<Activity> getWorkerActivities(@PathVariable Long id,
                                              @RequestHeader String token) {
        roleService.checkTokenRole(token, managingRoles);
        return activityService.getWorkerActivities(id);
    }

    @SneakyThrows
    @PatchMapping("/activity/{id}")
    public Activity updateActivity(@RequestHeader String token,
                                  @PathVariable Long id,
                                  @RequestBody SingleActivityDTO singleActivityDTO) {
        roleService.checkTokenRole(token, managingRoles);
        return activityService.updateActivity(id, singleActivityDTO);
    }
}
