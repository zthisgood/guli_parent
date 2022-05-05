//package com.atgui.eduneo4j.utils;
//
//import com.atgui.eduneo4j.config.Neo4jConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.neo4j.driver.v1.*;
//import org.neo4j.driver.v1.types.Node;
//import org.neo4j.driver.v1.types.Path;
//import org.neo4j.driver.v1.types.Relationship;
//import org.neo4j.driver.v1.util.Pair;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.util.*;
//import java.util.Map.Entry;
//
//@Slf4j
//@Component
//public class Neo4jUtil {
//    @Autowired
//    private Neo4jConfig neo4jConfig;
//
//    public boolean isNeo4jOpen() {
//        try (Session session = neo4jConfig.neo4jSession()) {
//            log.info("连接成功：" + session.isOpen());
//            return session.isOpen();
//        } catch (Exception e) {
//            log.error("连接异常：" + e.getMessage());
//            return false;
//        }
//    }
//
//    public StatementResult excuteCypherSql(String cypherSql) {
//        StatementResult result = null;
//        try (Session session = neo4jConfig.neo4jSession()) {
//            log.info("cypher语句：" + cypherSql);
//            result = session.run(cypherSql);
//            session.close();
//        } catch (Exception e) {
//            throw e;
//        }
//        return result;
//    }
//}
