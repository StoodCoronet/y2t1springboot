package com.stu_course;

import com.stu_course.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//@MapperScan("com.stu_course.mapper") //扫描的mapper

@SpringBootApplication
public class StuCourseApplication {

    public static void main(String[] args) {
        //SpringApplication.run(StuCourseApplication.class, args);
        ApplicationContext applicationContext =
                SpringApplication.run(StuCourseApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);
        //SpringUtil.printBean();

    }

}
