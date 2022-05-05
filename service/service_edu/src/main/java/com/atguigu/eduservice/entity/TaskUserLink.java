package com.atguigu.eduservice.entity;

import com.atguigu.eduservice.util.DateUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @Author: ys
 * @Date: 2021-03-12 15:37:41
 * @Description:
 */
@Data
public class TaskUserLink implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private Integer questionFraction;
    /**
     *
     */
    private Integer taskId;
    /**
     *
     */
    private Integer questionId;
    /**
     *
     */
    private String createTime;
    /**
     *
     */
    private String updateTime;

    public void generateUpdateTime(){
        String currentDateTime = DateUtil.getCurrentDateTime();
        this.setUpdateTime(currentDateTime);
    }

}
