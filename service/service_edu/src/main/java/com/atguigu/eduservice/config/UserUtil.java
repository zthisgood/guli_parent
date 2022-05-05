package com.atguigu.eduservice.config;

import com.atguigu.eduservice.entity.EduUser;
import com.atguigu.eduservice.util.LoginErrorException;
import com.atguigu.eduservice.util.SystemCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
public class UserUtil {

    /**
     * 获取用户及权限
     */
    public EduUser getUser(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        EduUser user = (EduUser)request.getSession().getAttribute("user");
        if (user==null){
            return user;
        }
        String token=user.getIdentity();
        if (StringUtils.isEmpty(token)){
            throw new LoginErrorException(SystemCode.TOKEN_ERROR,"token为空");
        }
        return user;
    }

}
