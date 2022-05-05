package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduUser;
import com.atguigu.eduservice.entity.Student;
import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.service.StudentService;
import com.atguigu.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/eduservice/user/")
@CrossOrigin  //解决跨域
public class EduLoginController {

    @Autowired
     private TeacherService teacherService;

    @Autowired
    private StudentService studentService;


    /**
     * login
     * @param user
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody EduUser user, HttpServletRequest request) {
        if(("teacher").equals(user.getIdentity())){
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            wrapper.eq("username", user.getUsername());
            wrapper.eq("password", user.getPassword());
            Teacher tea = teacherService.getOne(wrapper);
            if(tea != null){
                user.setId(tea.getId());
                request.getSession().setAttribute("user", user);
                return R.ok().data("token", user.getIdentity());
            }
        } else if (("student").equals(user.getIdentity())){
            QueryWrapper<Student> wrapper = new QueryWrapper<>();
            wrapper.eq("username", user.getUsername());
            wrapper.eq("password", user.getPassword());
            Student stu = studentService.getOne(wrapper);
            if(stu != null){
                user.setId(stu.getId());
                request.getSession().setAttribute("user", user);
                return R.ok().data("token", user.getIdentity());
            }
        }
        return R.error();
    }

    @PostMapping("logout")
    public R logOut(EduUser user){
        System.out.println("123");
        return R.ok().data("token", "");
    }

    @PostMapping("userLogin")
    public R userLogin(EduUser user){
        return R.ok().data("token", "admin");
    }

    /**
     * userinfo
     * @param request
     * @return
     */
    @GetMapping("info")
    public R info(HttpServletRequest request) {
        EduUser user = (EduUser) request.getSession().getAttribute("user");
        String a = user.getIdentity();
        return R.ok().data("roles",a.split(",")).data("name",user.getUsername()).data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
