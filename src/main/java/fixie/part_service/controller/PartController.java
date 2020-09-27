package fixie.part_service.controller;

import fixie.common.Roles;
import fixie.common.service.RoleService;
import fixie.part_service.dto.PartDTO;
import fixie.part_service.entity.Part;
import fixie.part_service.service.PartService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PartController {
    private final PartService partService;
    private final RoleService roleService;

    public PartController(PartService partService, RoleService roleService) {
        this.partService = partService;
        this.roleService = roleService;
    }

    @SneakyThrows
    @PostMapping("/part")
    public Part addPart(/*@RequestHeader String token,*/
                        @Valid @RequestBody PartDTO partDTO) {
//        roleService.checkTokenRole(token, Roles.ADMIN);
        return partService.addPart(partDTO);
    }

    @SneakyThrows
    @DeleteMapping("/part/{id}")
    public Optional<Part> deletePart(@RequestHeader String token,
                                     @PathVariable Long id) {
        roleService.checkTokenRole(token, Roles.ADMIN);
        return partService.deletePart(id);
    }

    @GetMapping("/parts")
    public List<Part> getParts() {
        return partService.getParts();
    }
}
