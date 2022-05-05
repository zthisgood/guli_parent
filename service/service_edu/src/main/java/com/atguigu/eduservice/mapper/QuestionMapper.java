package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author:ivern
 * @since 2022-02-25
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<Integer> getFillQuestionList();

    List<Integer> getMultiQuestionList();

    /**
     * 通过知识点获取
     * 获取相关的题目
     * @return
     */
    List<Question> querySomeTitleByKnowledge(@Param("knowledge") String knowledge);

    /**
     * 查询某用户的知识点
     * 范围情况
     * @param userId
     * @return
     */
    List<String> queryUserKnowledge(@Param("userId") Integer userId);
}
