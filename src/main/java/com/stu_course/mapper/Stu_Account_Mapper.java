package com.stu_course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Stu_Account_Mapper {
    int insertAccountItem(@Param("0") String stu_no,
                      @Param("1") double account_item,
                      @Param("2") String account_datetime,
                      @Param("3") String account_source);
}
