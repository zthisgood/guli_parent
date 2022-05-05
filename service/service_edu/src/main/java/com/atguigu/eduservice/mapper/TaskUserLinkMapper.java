/**
 * 	Copyright 2020 www.zj.cn
 *
 * 	All right reserved
 *
 * 	Create on 2020/5/20 05:20
 */
package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.TaskUserLink;
import com.atguigu.eduservice.entity.vo.QuestionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface TaskUserLinkMapper extends BaseMapper<TaskUserLink> {

    /**
     * 1
     * @param taskId
     * @return
     */
    List<QuestionVO> queryPaperQuestionsByTeacher(@Param("taskId") Integer taskId);

    List<TaskUserLink> queryByList(Integer paperId);

    Integer saveTaskQuestionBookBatch(@Param("taskUserLinks") List<TaskUserLink> taskUserLinks);
}
