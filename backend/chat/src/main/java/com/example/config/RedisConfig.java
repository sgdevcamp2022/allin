package com.example.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@ConfigurationProperties("spring.data.redis")
public class RedisConfig {

  private final String host;
  private final int port;

  @Value("${spring.data.redis.cluster.nodes}")
  private final List<String> clusterNodes;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterNodes);
    clusterConfiguration.clusterNode(host, port);
    return new LettuceConnectionFactory(clusterConfiguration);
  }

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer(
    RedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
    return redisTemplate;
  }
}
