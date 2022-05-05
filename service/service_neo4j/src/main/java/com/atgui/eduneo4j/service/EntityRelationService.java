package com.atgui.eduneo4j.service;

import com.atgui.eduneo4j.entity.EntityRelation;
import com.atgui.eduneo4j.entity.RelationShip;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
public interface EntityRelationService extends IService<EntityRelation> {

    /**
     * 1
     * @return obj
     */
    List<RelationShip> listEntityRelationShip();

    boolean insertEntityRelationFromRelationShip(RelationShip relationShip);

    List<RelationShip> listRelationShipByParam(String entity, Integer relationId);
}
