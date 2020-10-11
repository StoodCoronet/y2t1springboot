package com.stu_course.service_mybatis;

import com.stu_course.entity.Student;
import com.stu_course.mapper.Stu_info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S_M_Student {
    @Autowired
    private Stu_info_Mapper stuInfoMapper;
    public Student getSingleStudent(String stu_no){
     return stuInfoMapper.getSingleStudent(stu_no);
    }
    public List<Student> getStudentPage(String field, String condition,
                                        int page_number, int page_size){
        String whereCondition = " 1=1 ";
        if (!field.equals("default")){
            whereCondition += " and " + field +
                    " like '%" + condition + "%'";
        }
        page_number = (page_number - 1)* page_size;
        return  stuInfoMapper.getStudentPage(whereCondition,
                page_number, page_size);
    }
    public int deleteStudent(String stu_no){
        return stuInfoMapper.deleteStudent(stu_no);
    }
    public int insertStudent(Student student){
        return stuInfoMapper.insertStudent(student);
    }
}
