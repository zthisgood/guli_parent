package com.atgui.eduneo4j.mapper;

import com.atgui.eduneo4j.entity.EntityRelation;
import com.atgui.eduneo4j.entity.RelationShip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@Mapper
public interface EntityRelationMapper extends BaseMapper<EntityRelation> {

    /**
     * 查询联合对象
     * @return obj
     */
    List<RelationShip> getUnionRelation();

    /**
     * none
     * @param relationShip
     */
    boolean insertRelationFromRlsp(RelationShip relationShip);

    List<RelationShip> queryRelationShip(String entity, Integer relationId);
}
