package Ecommerce.Order.processing.system.src.service;

import Ecommerce.Order.processing.system.src.dto.OrderPlacedEvent;
import org.springframework.kafka.support.Acknowledgment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryListener {
    @KafkaListener(topics = "order-placed", groupId = "inventory-service-group", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void handleOrderPlaced(OrderPlacedEvent event, Acknowledgment acks) {
        try {
            event.getItems().forEach(item -> {
                log.info("Items id :{}", item.getId());
                acks.acknowledge();
            });
        } catch (Exception exception) {
            log.error("Exception Occured :{}", exception);
        }
    }
}
