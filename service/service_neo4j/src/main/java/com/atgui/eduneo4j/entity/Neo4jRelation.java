package com.atgui.eduneo4j.entity;

import java.io.Serializable;

public class Neo4jRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String from;

    private String to;

    private String relation;

    private String note;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
