package com.atguigu.eduservice.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @File: TaskTest
 * @Author: ivern
 * @Date: 2021-03-18 17:37:20
 * @Description:
 */
@Data
public class TaskTest implements Serializable {

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer taskId;
    /**
     *
     */
    private Integer titleId;
    /**
     * 标准答案
     */
    private String questionAnswer;
    /**
     * 学生答案
     */
    private String answer;
    /**
     * 本题分数
     */
    private Integer questionFraction;
    /**
     *
     */
    private String userId;
    /**
     *
     */
    private String userName;
    /**
     *
     */
    private String createTime;

    /**
     * 获取正确的
     * 学生答案
     */
    public void filterCorrectAnswer(){
        Integer selectType = 1;
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        if ((this.answer!=null)&&(list1.contains(this.answer.substring(0,1)))){
            this.answer = this.answer.substring(0,1);
        }
    }

}
