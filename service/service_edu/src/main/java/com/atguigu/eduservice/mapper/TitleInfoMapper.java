/**
 * 	Copyright 2020 www.zj.cn
 *
 * 	All right reserved
 *
 * 	Create on 2020/5/20 05:20
 */
package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.TitleInfo;
import com.atguigu.eduservice.entity.query.TitlePageQuery;
import com.atguigu.eduservice.entity.vo.TitleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TitleInfoMapper extends BaseMapper<TitleInfo> {

    List<TitleVO> queryPage(TitlePageQuery query);


    TitleVO queryTitleInfo(Integer titleId);
    //classId 查询试题
//    List<TitleInfo> queryTitleByClassId(@Param("classId") Integer classId, @Param("subjectId")Integer subjectId);
    //在一个难度区间
    List<TitleInfo> queryTitleByDifficulty(@Param("difficulty1") Double difficulty1, @Param("difficulty2") Double difficulty2, @Param("classId") Integer classId);

    List<TitleInfo> queryListByTitleId(@Param("titleIdList") List<Integer> titleIdList);

    List<TitleInfo> queryListByTitleIdE(@Param("titleIdList") List<Integer> titleIdList);
}
