package org.example.sample_quarkus_debezium;

import io.debezium.testing.testcontainers.DebeziumContainer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@QuarkusTest
public class QuarkusDebeziumTestContainersTest {

    private Network network = Network.newNetwork();

    @Container
    private KafkaContainer kafkaContainer =
            new KafkaContainer()
                    .withNetwork(network);

    @Container
    public PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("debezium/postgres:11")
                    .withNetwork(network)
                    .withNetworkAliases("postgres");

    @Container
    public DebeziumContainer debeziumContainer =
            new DebeziumContainer("debezium/connect:1.2.0.Beta2")
                    .withNetwork(network)
                    .withKafka(kafkaContainer)
                    .dependsOn(kafkaContainer);

    @Test
    public void should_run_test() {
        Assertions.assertTrue(true);
    }
}
