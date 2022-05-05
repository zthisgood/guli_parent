package com.atguigu.eduservice.entity.vo;

import com.atguigu.eduservice.entity.Question;
import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionVO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer tulId;
    private String fraction;
    private String updateTime;

    private Question question;

}
