package com.stu_course.controller;

import com.stu_course.entity.Stu_TimeTable;
import com.stu_course.entity.Student;
import com.stu_course.service.SStudent;
import com.stu_course.service_mybatis.S_M_Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CStudent_Json {
    @Autowired
    private SStudent student_from_autowired;
    @Autowired
    private S_M_Student s_m_student;

    @RequestMapping("get_single_student_json")
    public Student getSingleStudentJson(HttpServletRequest request){
        String stu_no = request.getParameter("stu_no");
        System.out.println(s_m_student.getSingleStudent(stu_no));
        return s_m_student.getSingleStudent(stu_no);
    }
    @RequestMapping(value = "get_timetable", method={RequestMethod.GET})
    public List<Stu_TimeTable> getTimeTable(HttpServletRequest request){
        String stu_no = request.getParameter("stu_no");
        List<Stu_TimeTable> timeTable = null;
        timeTable = student_from_autowired.getTimeTable(stu_no);
        return timeTable;
    }
}
