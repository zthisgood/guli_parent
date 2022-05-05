package com.atgui.eduneo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@Data
@NodeEntity(label = "knowledge")
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private String kind;

    @Property
    private String state;

    @Property
    private String identity;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Entity{" +
        "id=" + id +
        ", name=" + name +
        ", kind=" + kind +
        ", state=" + state +
        "}";
    }
}
