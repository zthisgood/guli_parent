package com.atgui.eduneo4j.mapper;

import com.atgui.eduneo4j.entity.Entity;
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
public interface EntityMapper extends BaseMapper<Entity> {

    /**
     * 查询list
     * @return 1
     */
    List<Entity> listEntity();
}
