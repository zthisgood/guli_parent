/**
 * 	Copyright 2020 www.zj.cn
 *
 * 	All right reserved
 *
 * 	Create on 2020/5/20 05:20
 */
package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.TaskInfo;
import com.atguigu.eduservice.entity.query.TaskQuery;
import com.atguigu.eduservice.entity.vo.TaskPageVO;
import com.atguigu.eduservice.entity.vo.TaskTitleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;



public interface TaskInfoMapper extends BaseMapper<TaskInfo> {

    /**
     * 式卷页面
     * @return
     */
    List<TaskPageVO> queryPage(TaskQuery query);

    /**
     * 教师管理员  试卷页面
     * @param query
     * @return
     */
    List<TaskInfo> queryTaskList(TaskQuery query);

    List<TaskTitleVO> queryTitleTask(Integer taskId);
}
