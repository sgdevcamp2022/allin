package com.example.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
@EnableRedisRepositories
@EnableMongoAuditing
@EnableDiscoveryClient
public class CommonConfig {

}
