package com.example.config;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@EnableMongoAuditing
public class CommonConfig {

}
