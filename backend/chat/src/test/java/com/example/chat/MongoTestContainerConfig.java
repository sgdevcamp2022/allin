package com.example.chat;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.ModifierSupport;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoTestContainerConfig implements BeforeAllCallback {

  private static final String MONGO_IMAGE = "mongo:5.0.14";

  private static final int MONGO_PORT = 27017;
  private static final String DATABASE = "chat";

  private MongoDBContainer mongoDB;

  @Override
  public void beforeAll(ExtensionContext context) {
    if (context.getTestClass().isPresent()) {
      Class<?> currentClass = context.getTestClass().get();
      if (isNestedClass(currentClass)) {
        return;
      }
    }

    mongoDB = new MongoDBContainer(DockerImageName.parse(MONGO_IMAGE))
      .withExposedPorts(MONGO_PORT);
    mongoDB.start();
    System.setProperty("spring.data.mongodb.host", mongoDB.getHost());
    System.setProperty("spring.data.mongodb.port", String.valueOf(mongoDB.getMappedPort(MONGO_PORT
    )));
    System.setProperty("spring.data.mongodb.database", DATABASE);
  }

  private boolean isNestedClass(Class<?> currentClass) {
    return !ModifierSupport.isStatic(currentClass) && currentClass.isMemberClass();
  }

}
