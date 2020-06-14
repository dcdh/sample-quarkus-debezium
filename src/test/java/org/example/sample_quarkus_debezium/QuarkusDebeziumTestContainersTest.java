package org.example.sample_quarkus_debezium;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(DebeziumTestResources.class)
public class QuarkusDebeziumTestContainersTest {

    @Test
    public void should_run_test() {
        Assertions.assertTrue(true);
    }
}
