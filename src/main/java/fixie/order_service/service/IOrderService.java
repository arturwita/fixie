package fixie.order_service.service;

import fixie.order_service.dto.OrderDTO;
import fixie.order_service.entity.Order;
import fixie.order_service.exception.OrderNotFoundException;
import fixie.order_service.exception.UnknownStatusException;

import java.util.List;

public interface IOrderService {

    List<Order> getOrders();

    Order createOrder(Long customerId);

    Order setOrderStatus(Long id, OrderDTO orderDTO)
            throws OrderNotFoundException, UnknownStatusException;
}
