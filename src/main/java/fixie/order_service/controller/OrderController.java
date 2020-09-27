package fixie.order_service.controller;

import fixie.common.InternalApiClient;
import fixie.common.Roles;
import fixie.common.service.RoleService;
import fixie.order_service.dto.OrderDTO;
import fixie.order_service.entity.Order;
import fixie.order_service.service.OrderService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final RoleService roleService;
    private final InternalApiClient internalApiClient = new InternalApiClient();

    private final String[] managingRoles = {Roles.MANAGER, Roles.ADMIN};

    public OrderController(OrderService orderService, RoleService roleService) {
        this.orderService = orderService;
        this.roleService = roleService;
    }

    @SneakyThrows
    @PostMapping("/order")
    public Order createOrder(@RequestHeader String token) {
        Long customerId = internalApiClient.getIdFromToken(token);
        return orderService.createOrder(customerId);
    }

    @SneakyThrows
    @PutMapping("/order/{id}")
    public Order setOrderStatus(@RequestHeader String token,
                                @RequestBody OrderDTO orderDTO,
                                @PathVariable Long id) {
        roleService.checkTokenRole(token, managingRoles);
        return orderService.setOrderStatus(id, orderDTO);
    }

    @SneakyThrows
    @GetMapping("/orders")
    public List<Order> getOrders(@RequestHeader String token) {
        roleService.checkTokenRole(token, managingRoles);
        return orderService.getOrders();
    }
}
