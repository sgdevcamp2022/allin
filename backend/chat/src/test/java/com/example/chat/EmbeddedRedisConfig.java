package com.example.chat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {
  @Value("${spring.data.redis.port}")
  private int port;
  private redis.embedded.RedisServer redisServer;


  @PostConstruct
  public void redisServer() throws IOException {
    port = isRedisRunning() ? findAvailablePort() : port;
    if (isArmMac()) {
      redisServer = new RedisServer(Objects.requireNonNull(getRedisFileForArcMac()),
        port);
    }
    if (!isArmMac()) {
      redisServer = new RedisServer(port);
    }

    redisServer.start();
  }

  @PreDestroy
  public void stopRedis(){

    if(redisServer != null){
      redisServer.stop();
    }
  }

  private boolean isRedisRunning() throws IOException {
    return isRunning(executeGrepProcessCommand(port));
  }

  public int findAvailablePort() throws IOException {

    for (int port = 10000; port <= 65535; port++) {
      Process process = executeGrepProcessCommand(port);
      if (!isRunning(process)) {
        return port;
      }
    }

    throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
  }

  /**
   * 해당 port를 사용중인 프로세스 확인하는 sh 실행
   */
  private Process executeGrepProcessCommand(int port) throws IOException {
    String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
    String[] shell = {"/bin/sh", "-c", command};
    return Runtime.getRuntime().exec(shell);
  }
  private boolean isRunning(Process process) {
    String line;
    StringBuilder pidInfo = new StringBuilder();

    try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

      while ((line = input.readLine()) != null) {
        pidInfo.append(line);
      }

    } catch (Exception e) {
    }

    return StringUtils.hasText(pidInfo.toString());
  }

  private boolean isArmMac() {
    return Objects.equals(System.getProperty("os.arch"), "aarch64") &&
      Objects.equals(System.getProperty("os.name"), "Mac OS X");
  }

  private File getRedisFileForArcMac() {
    try {
      return new ClassPathResource("binary/redis/redis-server-6.2.5-mac-arm64").getFile();
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }
}
