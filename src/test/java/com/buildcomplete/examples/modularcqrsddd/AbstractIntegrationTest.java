package com.buildcomplete.examples.modularcqrsddd;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
class AbstractIntegrationTest {
  private static final int MONGO_DB_CONTAINER_PORT = 27017;
  private static final int LOCAL_STACK_CONTAINER_PORT = 4566;
  private static final int JSON_SERVER_CONTAINER_PORT = 80;

  @Container
  static final MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:7.0")
      .withExposedPorts(MONGO_DB_CONTAINER_PORT)
      .waitingFor(Wait.forLogMessage(".*mongod startup complete.*", 1));

  @Container
  static final LocalStackContainer LOCAL_STACK_CONTAINER = new LocalStackContainer("3.4.0")
      .withServices(SQS, SNS)
      .withExposedPorts(LOCAL_STACK_CONTAINER_PORT)
      .withCopyFileToContainer(
          MountableFile.forHostPath(Paths.get("scripts/localstack/create-localstack-resources.sh"), 777),
          "/opt/code/localstack/create-localstack-resources.sh")
      .withEnv(Map.of(
          "AWS_DEFAULT_REGION", "eu-west-1",
          "AWS_ACCESS_KEY_ID", "dummy",
          "AWS_SECRET_ACCESS_KEY", "dummy",
          "AWS_ENDPOINT", "http://localhost:" + LOCAL_STACK_CONTAINER_PORT
      ));

  @Container
  static final MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer("mariadb:10.3.6");

  @Container
  static final GenericContainer JSON_SERVER = new GenericContainer("clue/json-server")
      .withExposedPorts(JSON_SERVER_CONTAINER_PORT)
      .withCopyFileToContainer(MountableFile.forHostPath(Paths.get("scripts/jsonserver/db.json")), "/data/db.json");

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("mongodb.connectionString", () -> MONGO_DB_CONTAINER.getConnectionString());
    registry.add("spring.cloud.aws.endpoint", () -> LOCAL_STACK_CONTAINER.getEndpoint().toString());
    registry.add("spring.datasource.url", () -> MARIA_DB_CONTAINER.getJdbcUrl());
    registry.add("spring.datasource.username", () -> MARIA_DB_CONTAINER.getUsername());
    registry.add("spring.datasource.password", () -> MARIA_DB_CONTAINER.getPassword());
    registry.add("api.paymentbroker.url", () -> "http://localhost:" + JSON_SERVER.getMappedPort(JSON_SERVER_CONTAINER_PORT));
  }

  @BeforeAll
  static void createLocalstackResources() throws IOException, InterruptedException {
    LOCAL_STACK_CONTAINER.execInContainer("sh", "/opt/code/localstack/create-localstack-resources.sh");
  }
}
