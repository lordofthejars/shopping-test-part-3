package org.acme;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MariaDBContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class InfrastructureTestResource implements QuarkusTestResourceLifecycleManager {

    static MariaDBContainer<?> db = new MariaDBContainer<>("mariadb:10.3.6")
                                            .withDatabaseName("mydb")
                                            .withUsername("developer")
                                            .withPassword("developer");

    static KafkaContainer kafka = new KafkaContainer();

    @Override
    public Map<String, String> start() {
        db.start();
        kafka.start();

        return configurationParameters();
    }

    private Map<String, String> configurationParameters() {
        final Map<String, String> conf = new HashMap<>();
        conf.put("%int-test.quarkus.datasource.jdbc.url", db.getJdbcUrl());
        conf.put("%int-test.quarkus.datasource.username", "developer");
        conf.put("%int-test.quarkus.datasource.password", "developer");
        conf.put("%int-test.quarkus.datasource.db-kind", "mariadb");
        conf.put("%int-test.quarkus.hibernate-orm.database.generation", "drop-and-create");
 
        conf.put("%int-test.kafka.bootstrap.servers",  kafka.getBootstrapServers());
        conf.put("%int-test.mp.messaging.outgoing.delivery.connector", "smallrye-kafka");
        conf.put("%int-test.mp.messaging.incoming.delivery-warehouse.connector", "smallrye-kafka");
        return conf;
    }

    @Override
    public void stop() {
        if (db != null) {
            db.stop();
        }

        if (kafka != null) {
            kafka.stop();
        }
    }

}