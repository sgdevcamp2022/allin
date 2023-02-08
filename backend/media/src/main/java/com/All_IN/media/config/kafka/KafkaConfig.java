package com.All_IN.media.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = "application.yml")
public class KafkaConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic live() {
        NewTopic newTopic = new NewTopic("live-topic", 1, (short) 1);
        Map<String, String> configs = new HashMap<>();
        newTopic.configs(configs);
        return newTopic;
    }

    @Bean
    public NewTopic liveIndex() {
        NewTopic newTopic = new NewTopic("liveIndex-topic", 1, (short) 1);
        Map<String, String> configs = new HashMap<>();
        newTopic.configs(configs);
        return newTopic;
    }
}
