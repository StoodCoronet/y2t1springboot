package com.stu_course.filter;

import com.stu_course.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class MVCInterceptor extends HandlerInterceptorAdapter {
    @Override
    //本程序不能处理Android和h5小程序过来的请求，
    // 因为此类访问都是ajax类型，没有session
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler){
        boolean flag = true;
        //打印出调用请求的URL地址
        String requestURL = request.getRequestURI();
        System.out.println(requestURL);
       Student student = (Student) request.getSession()
               .getAttribute("a_stu_info");
        if (student == null){//session不存在则跳转到重新登录
            flag = false;
            try {
                response.sendRedirect("show_login_web");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //以下添加权限检测代码，如果不能执行此操作，可以建议过滤掉。

        return  flag;
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView){
        /*
        System.out.println(request.getSession().getAttribute("stu_code"));
        if(request.getSession().getAttribute("stu_code") == null) {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        String requestURL = request.getRequestURI();
        System.out.println(requestURL);
    }
}
