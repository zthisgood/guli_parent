package com.atguigu.eduservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.annotations.Property;

import java.io.Serializable;

@Data
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    @TableField
    private String question;
    @TableField
    private String analysis;
    @TableField
    private String answer;
    @TableField
    private Integer type;
    @TableField
    private String address;
    @TableField
    private String knowl;
    @TableField(exist = false)
    private String stuAnswer;
    @TableField
    private String choice;
    @TableField(exist = false)
    private String choice1;
    @TableField(exist = false)
    private String choice2;
    @TableField(exist = false)
    private String choice3;
    @TableField(exist = false)
    private String choice4;

    /**
     * 分割选择题
     * 选项
     */
    public void splitChoices(){
        Integer selectType = 1;
        if(selectType.equals(this.type)){
            this.choice1 = this.choice.split("B")[0];
            this.choice2 = this.choice.substring(this.choice.indexOf("B"),this.choice.indexOf("C"));
            this.choice3 = this.choice.substring(this.choice.indexOf("C"),this.choice.indexOf("D"));
            this.choice4 = this.choice.substring(this.choice.indexOf("D"));
        }
    }
    /**
     * 生成前台界面的答案
     */
    public void reWriteAnswer(){
        Integer selectType = 1;
        if (this.type.equals(selectType)&this.stuAnswer!=null){
            int i = "ABCD".indexOf(this.stuAnswer);
            switch (i){
                case 1:
                    this.stuAnswer = this.choice2;
                    break;
                case 2:
                    this.stuAnswer = this.choice3;
                    break;
                case 3:
                    this.stuAnswer = this.choice4;
                    break;
                case 0:
                    this.stuAnswer = this.choice1;
                    break;
                default:
            }
        }
    }

    /**
     * 获取正确的
     * 学生答案
     */
    public void filterCorrectAnswer(){
        Integer selectType = 1;
        if (selectType.equals(this.type)){
            this.stuAnswer = this.stuAnswer.substring(0,1);
        }
    }
}
