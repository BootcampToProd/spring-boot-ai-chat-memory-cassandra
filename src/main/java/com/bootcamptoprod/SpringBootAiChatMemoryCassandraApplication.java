package com.bootcamptoprod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAiChatMemoryCassandraApplication {

    public static void main(String[] args) {

        // --- Configure Advanced DataStax Driver Logging via System Properties ---
        // These properties are set *before* Spring Boot initializes the Cassandra driver,
        // ensuring the driver picks them up early. They provide fine-grained control
        // over how the driver logs query executions, which is useful for debugging.

        // Explicitly tell the driver to use the RequestLogger class for tracking.
        // Using the index '.0' as it expects a list via the newer 'classes' property.
        System.setProperty("datastax-java-driver.advanced.request-tracker.classes.0", "RequestLogger");

        // Enable logging for successful queries.
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.success.enabled", "true");

        // Enable logging for queries considered "slow".
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.slow.enabled", "true");

        // Enable logging for queries that result in errors.
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.error.enabled", "true");

        // IMPORTANT: Enable logging of query parameter values (e.g., values bound to '?').
        // Very useful for debugging, but can be verbose. Use with caution in production.
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.show-values", "true");

        // Limit the length of logged parameter values to avoid excessively long log lines.
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.max-value-length", "200");

        // Limit the number of parameter values logged per query.
        System.setProperty("datastax-java-driver.advanced.request-tracker.logs.max-values", "200");
        // --- End of Driver Logging Configuration ---

        // Run the Spring Boot application.
        SpringApplication.run(SpringBootAiChatMemoryCassandraApplication.class, args);
    }
}
