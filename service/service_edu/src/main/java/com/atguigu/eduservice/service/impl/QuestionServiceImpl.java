package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Question;
import com.atguigu.eduservice.mapper.QuestionMapper;
import com.atguigu.eduservice.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
}
