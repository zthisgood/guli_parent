package com.atgui.eduneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories(basePackages = "com.atgui.eduneo4j.repository")
@SpringBootApplication
public class Neo4jSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(Neo4jSpringBootApplication.class, args);
    }
}
