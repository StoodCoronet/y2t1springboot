package com.stu_course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
//@RequestMapping("/hello")
public class Control_Test {
    @RequestMapping("/test_javaEE")
    public String testJavaEE(){
        return "test_web";
    }
}
