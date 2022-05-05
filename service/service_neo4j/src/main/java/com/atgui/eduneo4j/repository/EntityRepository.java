package com.atgui.eduneo4j.repository;

import com.atgui.eduneo4j.entity.Entity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface EntityRepository extends Neo4jRepository<Entity, Long> {

    /**
     * @param node
     * @return 1
     */
    @Query("MATCH(n)-[r]->(m:knowledge) where m.name={node} return n,m")
    List<Entity> queryRelativeEntity(@Param("node")String node);

}
