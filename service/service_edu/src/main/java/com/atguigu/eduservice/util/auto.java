package com.atguigu.eduservice.util;

import org.junit.Test;

/**
 * @author YS
 * @version 1.0
 * @date 2020-05-08 11:56
 */
public class auto {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://106.14.159.41:3306/cat";
    private static final String USERNAME = "cat";
    private static final String PASSWORD = "cat123";
    private static final String TABLE ="zj_paper_user";

    @Test
    public  void testDomain() throws Exception {
        AutoCodeUtil.autoDomain(URL,USERNAME,PASSWORD,TABLE,"cn.exam.domain.zj","zq-exam-domain");
        AutoCodeUtil.autoMapper(TABLE,"cn.exam.dao.mapper.zj","zq-exam-dao");

    }
}
