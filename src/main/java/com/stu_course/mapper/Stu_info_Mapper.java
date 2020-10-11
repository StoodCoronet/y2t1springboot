package com.stu_course.mapper;

import com.stu_course.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper //时代变了，要加@Mapper。以前不要的。
@Repository //需要配合main()主程序加注解 @MapperScan("com.stu_course.mapper")
public interface Stu_info_Mapper {
     Student getSingleStudent(@Param("0") String stu_no);
     List<Student> getStudentPage(@Param("whereCondition") String whereCondition,
                                  @Param("1") int page_number,
                                  @Param("2") int page_size);
     int deleteStudent(@Param("0") String stu_no);
     int insertStudent(Student student);
     List<Student> getStudentPageByDynamicSql(@Param("field") String field,
                                              @Param("1") String condition,
                                              @Param("2") int page_number,
                                              @Param("3") int page_size);
     int updateAccount(@Param("0") String stu_no,
                       @Param("1") double money);
}
