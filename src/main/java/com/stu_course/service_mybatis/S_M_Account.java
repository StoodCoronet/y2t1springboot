package com.stu_course.service_mybatis;

import com.stu_course.mapper.Stu_Account_Mapper;
import com.stu_course.mapper.Stu_info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class S_M_Account {
    @Autowired private Stu_Account_Mapper stu_account_mapper;
    @Autowired private Stu_info_Mapper stu_info_mapper;

    @Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT, readOnly = false)
    public int depositAccount(String stu_no,
                              String payMethod,
                              double money){
        int i = 0;
        //1:插入充值记录，
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = dateFormat.format( now );
        i = stu_account_mapper.insertAccountItem(stu_no, money, datetime, payMethod);
        //2：修改stu_account信息。
        i = stu_info_mapper.updateAccount(stu_no, money);
        return i;
    }
}
