package com.atgui.eduneo4j.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
public class Context implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String rawContext;

    private String entity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRawContext() {
        return rawContext;
    }

    public void setRawContext(String rawContext) {
        this.rawContext = rawContext;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Context{" +
        "id=" + id +
        ", rawContext=" + rawContext +
        ", entity=" + entity +
        "}";
    }
}
