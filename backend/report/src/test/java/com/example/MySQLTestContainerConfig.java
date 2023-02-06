package com.example;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.ModifierSupport;
import org.testcontainers.containers.MySQLContainer;

public class MySQLTestContainerConfig implements BeforeAllCallback {

  private static final String MYSQL_IMAGE = "mysql:8";
  private MySQLContainer mySQL;

  @Override
  public void beforeAll(ExtensionContext context) {
    if (context.getTestClass().isPresent()) {
      Class<?> currentClass = context.getTestClass().get();
      if (isNestedClass(currentClass)) {
        return;
      }
    }
    mySQL = new MySQLContainer(MYSQL_IMAGE);
    mySQL.start();
    System.setProperty("spring.datasource.url", mySQL.getJdbcUrl());
    System.setProperty("spring.datasource.username", mySQL.getUsername());
    System.setProperty("spring.datasource.password", mySQL.getPassword());
  }

  private boolean isNestedClass(Class<?> currentClass) {
    return !ModifierSupport.isStatic(currentClass) && currentClass.isMemberClass();
  }
}
