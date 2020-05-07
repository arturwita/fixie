package fixie.request_service.controller;

import fixie.common.InternalApiClient;
import fixie.common.Roles;
import fixie.common.service.RoleService;
import fixie.request_service.dto.RequestDTO;
import fixie.request_service.entity.Request;
import fixie.request_service.service.RequestService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RequestController {
    private final RequestService requestService;
    private final RoleService roleService;
    private final InternalApiClient internalApiClient = new InternalApiClient();

    private final String[] managingRoles = {Roles.MANAGER, Roles.ADMIN};

    public RequestController(RequestService partService, RoleService roleService) {
        this.requestService = partService;
        this.roleService = roleService;
    }

    @SneakyThrows
    @PostMapping("/request")
    public Request createRequest(@RequestHeader String token) {
        Long customerId = internalApiClient.getIdFromToken(token);
        return requestService.createRequest(customerId);
    }

    @SneakyThrows
    @PutMapping("/request/{id}")
    public Request setRequestStatus(@RequestHeader String token,
                                    @RequestBody RequestDTO requestDTO,
                                    @PathVariable Long id) {
        roleService.checkTokenRole(token, managingRoles);
        return requestService.setRequestStatus(id, requestDTO);
    }

    @SneakyThrows
    @GetMapping("/requests")
    public List<Request> getRequests(@RequestHeader String token) {
        roleService.checkTokenRole(token, managingRoles);
        return requestService.getRequests();
    }
}
