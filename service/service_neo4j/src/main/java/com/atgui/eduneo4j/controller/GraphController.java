package com.atgui.eduneo4j.controller;

import com.atgui.eduneo4j.entity.Entity;
import com.atgui.eduneo4j.entity.RelationShip;
import com.atgui.eduneo4j.repository.EntityRepository;
import com.atgui.eduneo4j.repository.RelationRepository;
import com.atgui.eduneo4j.service.EntityRelationService;
import com.atgui.eduneo4j.service.EntityService;
import com.atguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author 好学滴ivern
 */
@RestController
@RequestMapping("/neo4jservice/graph")
public class GraphController {

    @Autowired
    private EntityService entityService;

    @Autowired
    private EntityRelationService entityRelationService;

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private RelationRepository relationRepository;

    /**
     * 从neo4j获取图谱数据
     * 将图谱数据发送到vue
     * @return R
     */
    @PostMapping("/data")
    public R getGraphData(Integer type,String param){
        if (type==null){
            List<Entity> list1 = (List<Entity>) entityRepository.findAll();
            Collection<Map<String, String>> list2 = relationRepository.getAllRelationShip();
            return R.ok().data("node", list1).data("relation", list2);
        }else if (type==1){
            List<Entity> entities = entityRepository.queryRelativeEntity(param);
            Collection<Map<String, String>> list = relationRepository.getRelativeEntityByNode(param);
            return R.ok().data("relation",list).data("node", entities);
        }else {
            return R.ok().message("功能未开发");
        }
    }

    /**
     * @param
     * @param
     * @return R
     */
    @GetMapping("/shortPath")
    public R getShortestPath(String start, String end){
        List<Entity> entities = relationRepository.getShortestPath(start,end);
        Entity from = new Entity();
        Entity to = new Entity();
        List<Map<String, String>> relations = new ArrayList<>();
        for(int i=1;i<entities.size();i++){
            from = entities.get(i-1);
            to = entities.get(i);
            relations.add(relationRepository.getRelationShipByNode(from.getName(), to.getName()).get(0));
        }
        return R.ok().data("node", entities).data("relation",relations);
    }

    /**
     * 输入起点和终点
     * 输出路径
     * 图谱路径查询
     * @return
     */
    @PostMapping("/pathMatch")
    public R matchGraphPath(String start, String end){
        return R.ok().data("","");
    }


    /**
     * 从mysql获取图谱数据记录
     * 转存到neo4j database
     * 1.node数据节点
     * 2.relationship 对象
     * @return R
     */
    @GetMapping("/produce")
    public R makeTheGraph(){
        List<Entity> list2 = entityService.getAllEntity();
        for (Entity entity: list2) {
            entity.setIdentity(entity.getId().toString());
            entity.setId(null);
            entityRepository.save(entity);
        }
        List<RelationShip> list1 = entityRelationService.listEntityRelationShip();
        System.out.println(list1.size());
        for (RelationShip relationShip: list1){
            relationRepository.createRelation(relationShip.getEntity1(),
                    relationShip.getRelationChnName(),relationShip.getEntity2(),relationShip.getRelationNote());
        }
        return R.ok().data("data", "done");
    }

}