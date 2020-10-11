package com.stu_course.controller;

import com.stu_course.entity.Student;
import com.stu_course.service.SStudent;
import com.stu_course.util.SpringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CStudent {
    @RequestMapping("show_login")
    public String showLogin(){
        return  "login";
    }
    @RequestMapping("check_login_tomain")
    public String checkLoginToMain(HttpServletRequest request, Model model){
        SStudent student = new SStudent();
        String stu_no = request.getParameter("stu_no");
        String stu_pwd = request.getParameter("stu_pwd");
        //SStudent stu = (SStudent)SpringUtil.applicationContext.getBean("SStudent");
        Student stu = student.getStudentInfo(stu_no, stu_pwd);
        System.out.print("aaaaaaaaaaaaaaa");
        return "main";
    }
    @RequestMapping("check_login_tosession")
    public String checkLoginToSession(HttpServletRequest request){
        return "main";
    }
    @RequestMapping("check_login")
    public String checkLogin(HttpServletRequest request){
        String stuNo = request.getParameter("stu_no");
        String pwd = request.getParameter("stu_pwd");
        SStudent student = new SStudent();
        boolean flag = student.checkLogin(stuNo, pwd);
        if (flag)
            return "main";
        else
            return "redirect:show_login";
    }

}
