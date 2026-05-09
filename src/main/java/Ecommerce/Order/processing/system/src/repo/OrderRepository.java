package Ecommerce.Order.processing.system.src.repo;

import Ecommerce.Order.processing.system.src.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);

    // Find orders by status (PLACED, CONFIRMED, FAILED)
    List<Order> findByStatus(String status);

    List<Order> findByCustomerIdAndStatus(String customerId, String status);

    List<Order> findByPlacedAtAfter(Instant placedAt);

    boolean existsByIdAndStatus(String id, String status);
}
