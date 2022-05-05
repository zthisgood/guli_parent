package com.atgui.eduneo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

@Data
@RelationshipEntity(type = "关联")
public class RelationShip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String entity1;

    private String entity2;

    private Integer relationId;

    private String note;

    private String relationName;

    private String relationChnName;

    private String relationNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity1() {
        return entity1;
    }

    public void setEntity1(String entity1) {
        this.entity1 = entity1;
    }

    public String getEntity2() {
        return entity2;
    }

    public void setEntity2(String entity2) {
        this.entity2 = entity2;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationChnName() {
        return relationChnName;
    }

    public void setRelationChnName(String relationChnName) {
        this.relationChnName = relationChnName;
    }

    public String getRelationNote() {
        return relationNote;
    }

    public void setRelationNote(String relationNote) {
        this.relationNote = relationNote;
    }
}
