package fixie.order_service.service;

import fixie.order_service.dto.OrderDTO;
import fixie.order_service.entity.Order;
import fixie.order_service.entity.Status;
import fixie.order_service.exception.OrderNotFoundException;
import fixie.order_service.exception.UnknownStatusException;
import fixie.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Long customerId) {
        Order order = Order.builder()
                .customerId(customerId)
                .status(Status.OPEN)
                .build();

        orderRepository.save(order);

        return order;
    }

    @Override
    public Order setOrderStatus(Long id, OrderDTO orderDTO)
            throws OrderNotFoundException, UnknownStatusException {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (!orderOptional.isPresent()) throw new OrderNotFoundException();

        List<String> statuses = Status.getPossibleStatuses();
        if (!statuses.contains(orderDTO.status)) {
            throw new UnknownStatusException();
        }

        Order order = orderOptional.get();
        order.setStatus(orderDTO.status);

        orderRepository.save(order);

        return order;
    }
}
