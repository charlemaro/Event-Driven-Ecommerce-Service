package Ecommerce.Order.processing.system.src.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class TopicConfig {
    long retentionMs = 7 * 24 * 60 * 60 * 1000L;
    @Bean
    public NewTopic topicForOrder() {
        return TopicBuilder.name("order-placed")
                .partitions(6)
                .replicas(1)
                .config(org.apache.kafka.common.config.TopicConfig.RETENTION_MS_CONFIG, String.valueOf(retentionMs))
                .build();
    }


    @Bean
    public NewTopic inventoryReservedTopic() {
        return TopicBuilder.name("inventory-reserved")
                .partitions(6)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentProcess() {
        return TopicBuilder.name("payment-processed")
                .partitions(6)
                .replicas(1)
                .build();
    }
}
