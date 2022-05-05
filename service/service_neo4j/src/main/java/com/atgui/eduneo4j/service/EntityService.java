package com.atgui.eduneo4j.service;

import com.atgui.eduneo4j.entity.Entity;
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
public interface EntityService extends IService<Entity> {

    /**
     *
     * @return 2
     */
    List<Entity> getAllEntity();
}
