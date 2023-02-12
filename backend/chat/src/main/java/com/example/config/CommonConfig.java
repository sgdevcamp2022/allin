package com.example.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableConfigurationProperties(value = {RedisConfig.class})
@EnableRedisRepositories
@EnableMongoAuditing
//@EnableDiscoveryClient
@Configuration
public class CommonConfig {

}
