package com.example.chat;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.ModifierSupport;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisTestContainerConfig implements BeforeAllCallback {

  private static final String REDIS_IMAGE = "redis:7.0.8-alpine";
  private static final int REDIS_PORT = 6379;
  private GenericContainer redis;

  @Override
  public void beforeAll(ExtensionContext context) {
    if (context.getTestClass().isPresent()) {
      Class<?> currentClass = context.getTestClass().get();
      if (isNestedClass(currentClass)) {
        return;
      }
    }
    redis = new GenericContainer(DockerImageName.parse(REDIS_IMAGE))
      .withExposedPorts(REDIS_PORT);
    redis.start();
    System.setProperty("spring.data.redis.host", redis.getHost());
    System.setProperty("spring.data.redis.port", String.valueOf(redis.getMappedPort(REDIS_PORT
    )));
  }

  private boolean isNestedClass(Class<?> currentClass) {
    return !ModifierSupport.isStatic(currentClass) && currentClass.isMemberClass();
  }
}
