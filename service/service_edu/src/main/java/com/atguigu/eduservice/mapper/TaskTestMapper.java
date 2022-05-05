/**
 * 	Copyright 2020 www.zj.cn
 *
 * 	All right reserved
 *
 * 	Create on 2020/5/20 05:20
 */
package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.entity.TaskTest;
import com.atguigu.eduservice.entity.vo.TaskTestVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface TaskTestMapper extends BaseMapper<TaskTest> {

    /**
     * 查找学生task测试数据
     * @param userId
     * @param taskId
     * @return
     */
    List<TaskTestVO> queryStuTaskTest(@Param("userId") String userId, @Param("taskId") Integer taskId);

    List<Integer> queryIdByTaskId(Integer paperId);

    List<TaskTest> queryListById(@Param("ids")List<Integer> ids);

    List<TaskTest> queryTaskTestByUserIdAndTaskId(@Param("paperId") Integer paperId, @Param("userId") String userId);

    /**
     * 根据taskid 创建指定用户的task题目测试数据
     * @param taskId
     * @param userId
     * @return
     */
    Integer insertTaskTest(@Param("taskId")Integer taskId, @Param("userId") Integer userId);

    /**
     * 查询题目信息
     * @param ids
     * @return
     */
    List<TaskTest> getTitleFraction(@Param("ids") List<Integer> ids);

    /**
     * 查询所有的错题
     * @param userId
     * @return
     */
    List<Map<String,String>> getWrongTaskTestTitle(@Param("userId") String userId);

    /**
     * 获取问题id
     * @return
     */
    Question getQuestionById(@Param("titleId") Integer titleId);

}
