package Ecommerce.Order.processing.system.src.service;

import Ecommerce.Order.processing.system.src.dto.OrderPlacedEvent;
import Ecommerce.Order.processing.system.src.dto.PlacedOrderRequest;
import Ecommerce.Order.processing.system.src.model.Order;
import Ecommerce.Order.processing.system.src.model.OrderItem;
import Ecommerce.Order.processing.system.src.repo.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order placeOrder(PlacedOrderRequest request) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerId(request.getCustomerId());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setItems(request.getItems());
        BigDecimal totalAmount = request.getItems().stream().map(OrderItem::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");
        order.setCurrency("USD");
        order.setPlacedAt(Instant.now());
        List<OrderItem> items = request.getItems().stream().map(item -> {
            // This link is REQUIRED for JPA to save the foreign key
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());
        orderRepository.save(order);

        OrderPlacedEvent event = new OrderPlacedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getCustomerEmail(),
                order.getItems(),
                order.getTotalAmount(),
                order.getCurrency(),
                order.getPlacedAt(),
                "PLACED"
        );
        kafkaTemplate.send("order-placed", order.getId(), event)
                .whenComplete((result, exception) -> {
                    if (!Objects.isNull(exception)) {
                        log.info("Failed to publish Order :{}", order.getId(), exception);
                    } else {
                        log.info("Published the event for order {} ->partition :{},offset :{}"
                                , order.getId(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                    }
                });
        return order;
    }
}
