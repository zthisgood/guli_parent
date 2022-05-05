package com.atgui.eduneo4j.controller;

import com.atgui.eduneo4j.entity.Entity;
import com.atgui.eduneo4j.entity.EntityRelation;
import com.atgui.eduneo4j.entity.Relation;
import com.atgui.eduneo4j.entity.RelationShip;
import com.atgui.eduneo4j.service.EntityRelationService;
import com.atgui.eduneo4j.service.EntityService;
import com.atgui.eduneo4j.service.RelationService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 321
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/entityRelation")
@CrossOrigin(origins ="*",maxAge = 3600)
public class EntityRelationController {

    @Autowired
    private EntityService entityService;

    @Autowired
    private EntityRelationService entityRelationService;

    @Autowired
    private RelationService relationService;
    /**
     * 查询节点关系列表
     * @return 1
     */
    @GetMapping("/getAll")
    public R getEntityRelationShip(){
        List<RelationShip> list1 = entityRelationService.listEntityRelationShip();
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        List<Relation> list2 = relationService.list(wrapper);
        return R.ok().data("list", list1).data("relation", list2);
    }

    @GetMapping("/queryList")
    public R queryRelationShipByParam(String entity, String relationId){
        Integer r_id = null;
        if(relationId != null){
            r_id = Integer.parseInt(relationId);
        }
        List<RelationShip> list1 = entityRelationService.listRelationShipByParam(entity, r_id);
        QueryWrapper<Relation> wrapper = new QueryWrapper<>();
        List<Relation> list2 = relationService.list(wrapper);
        return R.ok().data("list",list1).data("relation", list2);
    }

    /**
     * 更新节点关系
     * @param relationShip
     * @return 1
     */
    @PostMapping("/update")
    public R updateRelationShip(@RequestBody RelationShip relationShip){
        EntityRelation entityRelation = new EntityRelation();
        entityRelation.setEntity1(relationShip.getEntity1());
        entityRelation.setEntity2(relationShip.getEntity2());
        entityRelation.setId(relationShip.getId());
        entityRelation.setRelationId(relationShip.getRelationId());
        entityRelation.setNote(relationShip.getNote());
        if(entityRelationService.updateById(entityRelation)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 移除指定的节点
     * @param identity
     * @return
     */
    @PostMapping("/remove")
    public R removeRelationShip(String identity){
        if(entityRelationService.removeById(Long.parseLong(identity))){
            System.out.println(identity);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 判断节点是否存在，如果节点不存在，
     * 创建指定的节点，然后创建关系
     * @param relationShip
     * @return
     */
    @PostMapping("/add")
    public R addAnRelationShip(@RequestBody RelationShip relationShip){
        QueryWrapper<Entity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", relationShip.getEntity1());
        if (entityService.count(wrapper) == 0){
            Entity entity = new Entity();
            entity.setName(relationShip.getEntity1());
            entityService.save(entity);
        }
        if (entityService.count(wrapper) == 0){
            Entity entity = new Entity();
            entity.setName(relationShip.getEntity2());
            entityService.save(entity);
        }
        if(entityRelationService.insertEntityRelationFromRelationShip(relationShip)){
            return R.ok();
        }
        return R.error();
    }
}

