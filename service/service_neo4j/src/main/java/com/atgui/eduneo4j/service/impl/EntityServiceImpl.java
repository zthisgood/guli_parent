package com.atgui.eduneo4j.service.impl;

import com.atgui.eduneo4j.entity.Entity;
import com.atgui.eduneo4j.mapper.EntityMapper;
import com.atgui.eduneo4j.service.EntityService;
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
public class EntityServiceImpl extends ServiceImpl<EntityMapper, Entity> implements EntityService {

    @Autowired
    EntityMapper entityMapper;

    @Override
    public List<Entity> getAllEntity() {
        return entityMapper.listEntity();
    }
}
