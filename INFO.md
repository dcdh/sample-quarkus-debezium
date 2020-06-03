# What is it

keywords: **Quarkus 1.5.0.Final**, **Debezium**, **testcontainers**, **integrations tests**, **docker**.

## How to run

From the console under the project run this:

```bash
docker pull debezium/postgres:11 && \
  docker pull debezium/connect:1.2.0.Beta2 && \
  docker pull confluentinc/cp-kafka:5.2.1 && \
  docker kill $(docker ps -aq); docker rm $(docker ps -aq); docker volume prune -f; \
  export TESTCONTAINERS_RYUK_DISABLED=true; \
  mvn clean test
```

Remarks:

- By using `@Testcontainers` containers lifecycle is automatically handled by it. This imply container creation before test and removal after test.
