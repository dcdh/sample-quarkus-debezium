# What is it

keywords: **Quarkus 1.5.0.Final**, **Debezium**, **testcontainers**, **integrations tests**, **docker**.

## How to run

### local

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

### OKD

> Connect using ssh

```bash
ssh damien@master.okd.local 'docker pull debezium/postgres:11' && \
  ssh damien@master.okd.local 'docker pull debezium/connect:1.2.0.Beta2' && \
  ssh damien@master.okd.local 'docker pull confluentinc/cp-kafka:5.2.1' && \
  ssh damien@master.okd.local 'docker pull adoptopenjdk/maven-openjdk8' && \
  ssh damien@master.okd.local 'docker pull docker:18.09.7-dind' && \
  scp -r -p openshift/jenkins-pipeline.yml damien@master.okd.local:/tmp && \
  ssh damien@master.okd.local 'oc process -f /tmp/jenkins-pipeline.yml | oc create -f - -n ci-cd' && \
  ssh damien@master.okd.local 'oc adm policy add-scc-to-user -z jenkins privileged -n ci-cd'
```

Remarks:

- `jenkins` is a service account used by jenkins stuff. We need to add `privilege` role to ensure it to create privilege containers.

## References

> testcontainers : https://www.testcontainers.org/
>
> testcontainers Spring Boot : https://github.com/testcontainers/testcontainers-spring-boot
>
> jenkins kubernetes : https://timmhirsens.de/posts/2019/07/testcontainers_on_jenkins_with_kubernetes/
