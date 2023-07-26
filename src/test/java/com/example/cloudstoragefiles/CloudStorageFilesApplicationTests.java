package com.example.cloudstoragefiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CloudStorageFilesApplicationTests {
    private static final int PORT_DB = 5432;
    private static final int PORT_SERVER = 5050;

    private static final String DATABASE_NAME = "CloudStorageFiles";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "1234";

    private static final Network CLOUD_NETWORK = Network.newNetwork();

    @Container
    public static PostgreSQLContainer<?> databaseContainer = new PostgreSQLContainer<>("postgres")
            .withNetwork(CLOUD_NETWORK)
            .withExposedPorts(PORT_DB)
            .withDatabaseName(DATABASE_NAME)
            .withUsername(DATABASE_USERNAME)
            .withPassword(DATABASE_PASSWORD);

    @Container
    public static GenericContainer<?> serverContainer = new GenericContainer<>("cloudstoragefiles")
            .withNetwork(CLOUD_NETWORK)
            .withExposedPorts(PORT_SERVER)
            .withEnv(Map.of("SPRING_DATASOURCE_URL", "jdbc:postgresql://database:" + PORT_DB + "/" + DATABASE_NAME))
            .dependsOn(databaseContainer);

    @Test
    void contextDatabase() {
        Assertions.assertTrue(databaseContainer.isRunning());
    }

    @Test
    void contextServer() {
        Assertions.assertFalse(serverContainer.isRunning());
    }

}
