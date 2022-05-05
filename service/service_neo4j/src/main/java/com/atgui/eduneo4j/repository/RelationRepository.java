package com.atgui.eduneo4j.repository;

import com.atgui.eduneo4j.entity.Entity;
import com.atgui.eduneo4j.entity.RelationShip;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface RelationRepository extends Neo4jRepository<RelationShip, Long> {

    @Query("match (n:knowledge{name:{from}}),(m:knowledge{name:{to}}) " +
    "create (n)-[:关联{note:{note},relation:{relation},start:n.identity,end:m.identity}]->(m)")
    void createRelation(@Param("from") String from,@Param("relation") String relation,
                        @Param("to") String to,@Param("note") String note);

    @Query("MATCH(n)-[r]->(m:knowledge) where m.name={node} return n.identity as start,m.identity as end," +
            "n.name as from,m.name as to,r.relation as relation,r.note as note")
    Collection<Map<String, String>> getRelativeEntityByNode(@Param("node") String node);

    @Query("MATCH (n)-[r]->(m) RETURN n.identity as start,m.identity as end,n.name as from,m.name as to," +
            "r.relation as relation,r.note as note")
    Collection<Map<String, String>> getAllRelationShip();

    @Query("MATCH (p1:knowledge{name:{start}}),(p2:knowledge{name:{end}})," +
            "p=shortestpath((p1)-[r*..4]->(p2)) return nodes(p)")
    List<Entity> getShortestPath(@Param("start") String start, @Param("end") String end);

    @Query("match (n)-[r]->(m) where n.name={from} and m.name={to} return n.identity as start,m.identity as end," +
            "n.name as from,m.name as to,r.relation as relation,r.note as note")
    List<Map<String,String>> getRelationShipByNode(@Param("from") String form, @Param("to") String to);
}
