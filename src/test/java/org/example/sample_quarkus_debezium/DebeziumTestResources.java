package org.example.sample_quarkus_debezium;

import io.debezium.testing.testcontainers.DebeziumContainer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

// https://github.com/quarkusio/quarkus-quickstarts/blob/master/kafka-quickstart/src/test/java/org/acme/kafka/KafkaResource.java
public class DebeziumTestResources implements QuarkusTestResourceLifecycleManager {

    private Network network = Network.newNetwork();

    private KafkaContainer kafkaContainer;

    private PostgreSQLContainer<?> postgresContainer;

    private DebeziumContainer debeziumContainer;

    @Override
    public Map<String, String> start() {
        kafkaContainer = new KafkaContainer()
                .withNetwork(network);
        postgresContainer = new PostgreSQLContainer<>("debezium/postgres:11")
                .withNetwork(network)
                .withNetworkAliases("postgres");
        debeziumContainer = new DebeziumContainer("debezium/connect:1.2.0.Beta2")
                .withNetwork(network)
                .withKafka(kafkaContainer)
                .dependsOn(kafkaContainer);
        kafkaContainer.start();
        postgresContainer.start();
        debeziumContainer.start();
        System.setProperty("kafka.bootstrap.servers", kafkaContainer.getBootstrapServers());
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        System.clearProperty("kafka.bootstrap.servers");
        if (kafkaContainer != null) {
            kafkaContainer.close();
        }
        if (postgresContainer != null) {
            postgresContainer.close();
        }
        if (debeziumContainer != null) {
            debeziumContainer.close();
        }
    }

}
