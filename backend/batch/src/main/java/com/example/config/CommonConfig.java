package com.example.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRedisRepositories
@EnableConfigurationProperties(value = {RedisConfig.class})
@EnableMongoAuditing
@EnableScheduling
@EnableBatchProcessing
@Configuration
public class CommonConfig {

}
