package com.stu_course.db;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbManage {
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/stu_course_info?serverTimezone=GMT%2B8");
        ds.setPassword("123456");
        ds.setUsername("root");
        return ds;
    }
}
