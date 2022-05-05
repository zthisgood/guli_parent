package com.atgui.eduneo4j.service.impl;

import com.atgui.eduneo4j.entity.EntityRelation;
import com.atgui.eduneo4j.entity.RelationShip;
import com.atgui.eduneo4j.mapper.EntityRelationMapper;
import com.atgui.eduneo4j.service.EntityRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@Service
public class EntityRelationServiceImpl extends ServiceImpl<EntityRelationMapper, EntityRelation> implements EntityRelationService {

    @Autowired
    EntityRelationMapper entityRelationMapper;

    @Override
    public List<RelationShip> listEntityRelationShip() {
        return entityRelationMapper.getUnionRelation();
    }

    @Override
    public boolean insertEntityRelationFromRelationShip(RelationShip relationShip) {
        return entityRelationMapper.insertRelationFromRlsp(relationShip);
    }

    /**
     * 根据节点名称和关系查询
     * @param entity
     * @param relationId
     * @return
     */
    @Override
    public List<RelationShip> listRelationShipByParam(String entity, Integer relationId) {
        return entityRelationMapper.queryRelationShip(entity,relationId);
    }

}
