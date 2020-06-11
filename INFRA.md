sh "sleep 999999999"
export GIT_COMMITTER_NAME=damien && export GIT_COMMITTER_EMAIL=damien && git clone https://github.com/dcdh/sample-quarkus-debezium.git
cd sample-quarkus-debezium && export TESTCONTAINERS_RYUK_DISABLED=true;mvn clean test


mvn help:effective-settings
mvn -s /home/jenkins/workspace/ci-cd/ci-cd-sample-quarkus-debezium-build-pipeline/settings.xml help:effective-settings