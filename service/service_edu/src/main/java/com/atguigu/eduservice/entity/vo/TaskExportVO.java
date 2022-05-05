package com.atguigu.eduservice.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author YS
 * @version 1.0
 * @date 2021-04-16 13:40
 */
@Data
public class TaskExportVO {
    @ExcelProperty(value = "题目名称", index = 0)
    private String titleName;
    @ExcelProperty(value = "题目答案", index = 1)
    private String titleAnswer;
    @ExcelProperty(value = "题目分数", index = 2)
    private Integer titleFraction;
    @ExcelProperty(value = "选项A", index = 3)
    private String choice1;
    @ExcelProperty(value = "选项B", index = 4)
    private String choice2;
    @ExcelProperty(value = "选项C", index = 5)
    private String choice3;
    @ExcelProperty(value = "选项D", index = 6)
    private String choice4;
    @ExcelIgnore
    private String paperName;
}
