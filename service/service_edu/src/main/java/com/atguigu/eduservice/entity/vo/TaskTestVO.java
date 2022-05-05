package com.atguigu.eduservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author YS
 * @version 1.0
 * @date 2021-03-19 13:30
 */
@Data
public class TaskTestVO {
    @TableId
    private Integer id;
    private Integer taskId;
    private String title;
    private Integer titleStatus;
    private String choices;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correctAnswer;
    private String answer;
    /**
     * 0填空题，1选择题
     */
    private String qType;
    private Integer titleFraction;
    private String paperName;
    private Integer examDate;
    private String userName;

    public void splitChoices(){
        String selectType = "1";
        if(selectType.equals(this.qType)){
            this.choice1 = this.choices.split("B")[0];
            this.choice2 = this.choices.substring(this.choices.indexOf("B"),this.choices.indexOf("C"));
            this.choice3 = this.choices.substring(this.choices.indexOf("C"),this.choices.indexOf("D"));
            this.choice4 = this.choices.substring(this.choices.indexOf("D"));
        }
   }

    /**
     * 生成前台界面的答案
     */
   public void reWriteAnswer(){
       String selectType = "1";
       if (this.qType.equals(selectType)&this.answer!=null){
           int i = "ABCD".indexOf(this.answer);
           switch (i){
               case 1:
                   this.answer = this.choice2;
                   break;
               case 2:
                   this.answer = this.choice3;
                   break;
               case 3:
                   this.answer = this.choice4;
                   break;
               case 0:
                   this.answer = this.choice1;
                   break;
               default:
           }
       }
   }

}
