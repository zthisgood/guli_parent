/**
 * 	Copyright 2020 www.zj.cn
 *
 * 	All right reserved
 *
 * 	Create on 2020/5/20 05:20
 */
package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.TaskUser;
import com.atguigu.eduservice.entity.query.TaskByUserIdQuery;
import com.atguigu.eduservice.entity.query.TaskUserQuery;
import com.atguigu.eduservice.entity.vo.AchievementExportVO;
import com.atguigu.eduservice.entity.vo.TaskByUserIdVO;
import com.atguigu.eduservice.entity.vo.TaskExportVO;
import com.atguigu.eduservice.entity.vo.TaskUserPapage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskUserMapper extends BaseMapper<TaskUser> {


    TaskUser queryTask(TaskUser taskUser);

    List<TaskUserPapage> queryPage(TaskUserQuery query);

    /**
     *
     * @return
     */
    List<Map<String,String>> queryPaperList();

    /**
     * 成绩导出
     */
    List<AchievementExportVO> queryExport();


    /**
     * 导出
     */
    List<TaskExportVO> queryTaskExport(Integer taskId);

    /**
     * 查询考完试卷
     *
     * @param query
     */
    List<TaskByUserIdVO> queryTaskByUserId(TaskByUserIdQuery query);

}
