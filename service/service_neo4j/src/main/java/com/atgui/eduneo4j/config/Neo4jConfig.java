//package com.atgui.eduneo4j.config;
//
//import org.neo4j.driver.v1.AuthTokens;
//import org.neo4j.driver.v1.Driver;
//import org.neo4j.driver.v1.GraphDatabase;
//import org.neo4j.driver.v1.Session;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// *     neo4j配置类:
// *         用以加载驱动以及配置连接到neo4j的各种信息，以及指定repository。
// */
////@Configuration
////@EnableTransactionManagement
////@EnableNeo4jRepositories(basePackages = "com.atgui.eduneo4j.repository")
//public class Neo4jConfig {
//    @Value("${spring.data.neo4j.uri}")
//    private String url;
//
//    @Value("${spring.data.neo4j.username}")
//    private String username;
//
//    @Value("${spring.data.neo4j.password}")
//    private String password;
//
//    @Bean(name = "session")
//    public Session neo4jSession() {
//        System.out.println(username+":"+password+":"+url);
//        Driver driver = GraphDatabase.driver(url, AuthTokens.basic(username, password));
//        return driver.session();
//    }
//}
//
