package com.stu_course.service;

import com.stu_course.db.DbManage;
import com.stu_course.entity.Stu_TimeTable;
import com.stu_course.entity.Student;
import com.stu_course.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.lang.model.element.NestingKind;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SStudent {
    public int editStudent(Student student){
        int flag = 0;
        String sqlTxt = "update stu_info set stu_name='" +
                student.getStu_name() + "', stu_phone='" +
                student.getStu_phone() + "', stu_pro='" +
                student.getStu_pro() + "', stu_sex='" +
                student.getStu_sex() + "', stu_class='" +
                student.getStu_class() + "', stu_age=" +
                student.getStu_age() +
                " where stu_no='" + student.getStu_no() + "'" ;
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        flag = jdbcTemplate.update(sqlTxt);

        return  flag;
    }
    public Student getSingleStudent(String stu_no){
        Student student = new Student();
        String sqlTxt = "select * from stu_info where stu_no='" + stu_no + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        student.setStu_no(list.get(0).get("stu_no").toString());
        student.setStu_name(list.get(0).get("stu_name").toString());
        student.setStu_phone(list.get(0).get("stu_phone").toString());
        student.setStu_pro(list.get(0).get("stu_pro").toString());
        student.setStu_sex(list.get(0).get("stu_sex").toString());
        student.setStu_age(Integer.parseInt(list.get(0).get("stu_age").toString()));
        student.setStu_class(list.get(0).get("stu_class").toString());

        return student;
    }
    public int insertStudent(Student student){
        int i = 0;
        String sqlTxt = "insert into stu_info " +
        " (stu_no, stu_name, stu_phone, stu_sex, stu_age, stu_pro, stu_class) values(" +
                "'" + student.getStu_no() + "'," +
                "'" + student.getStu_name() + "'," +
                "'" + student.getStu_phone() + "'," +
                "'" + student.getStu_sex() + "'," +
                ""  + student.getStu_age() + "," +
                "'" + student.getStu_pro() + "'," +
                "'" + student.getStu_class() + "')";
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        i = jdbcTemplate.update(sqlTxt);

        return  i;
    }
    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<Student>();
        String sqlTxt = "select * from stu_info";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list){
                Student student = new Student();
            student.setStu_no(map.get("stu_no").toString());
            student.setStu_name(map.get("stu_name").toString());
            student.setStu_pro(map.get("stu_pro").toString());
            student.setStu_phone(map.get("stu_phone").toString());
            student.setStu_sex(map.get("stu_sex").toString());
            student.setStu_age
                    (Integer.parseInt(map.get("stu_age").toString()));
            studentList.add(student);
        }
        return  studentList;
    }
    public Student getStudentInfo(String stu_no, String stu_pwd){
        Student student = null;

        return  student;
    }
    public List<Stu_TimeTable> getTimeTable(String stu_no){
        ArrayList<Stu_TimeTable> stu_timeTables = new ArrayList<Stu_TimeTable>();
        String sqlTxt = "SELECT  c.course_time, c.course_classroom, " +
                " c.course_name, s.stu_no , t.teacher_name " +
                " from course_info c INNER JOIN stu_course_info s" +
                " on s.course_no = c.course_no " +
                " INNER JOIN teacher_info t on t.teacher_no = c.teacher_no ";
        sqlTxt += " where stu_no = '" + stu_no + "'";
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate =(JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for(Map<String, Object> map : list){
            Stu_TimeTable stu_timeTable = new Stu_TimeTable();
            String course_time = map.get("course_time").toString();
            //System.out.println(course_time);
            String[] course_times = course_time.split("\\|");
            stu_timeTable.setXqj(course_times[0]);
            stu_timeTable.setStart(course_times[1]);
            stu_timeTable.setKs(course_times[2]);
            String info = map.get("course_name").toString()
                    + "@" + map.get("course_classroom").toString()
                    + "@" + map.get("teacher_name").toString();
            stu_timeTable.setInfo(info);
            stu_timeTables.add(stu_timeTable);
        }
        return stu_timeTables;
    }
    public Student checkLoginWeb(String stu_no, String stu_pwd){
        String sqlTxt = "select * from stu_info where stu_no='"
                + stu_no  +"' and stu_pwd = '"
                + stu_pwd + "'";
        System.out.println(sqlTxt);
        Student student = null;
        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil
                .applicationContext.getBean("jdbcTemplate");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlTxt);
        if (list.size() > 0){
            student = new Student();
            student.setStu_no(list.get(0).get("stu_no").toString());
            student.setStu_name(list.get(0).get("stu_name").toString());
            student.setStu_pro(list.get(0).get("stu_pro").toString());
            student.setStu_phone(list.get(0).get("stu_phone").toString());
            student.setStu_sex(list.get(0).get("stu_sex").toString());
            student.setStu_age
                    (Integer.parseInt(list.get(0).get("stu_age").toString()));
        }

        return student;
    }
    public boolean checkLogin(String stu_no, String stu_pwd){
        boolean flag = false;
        DbManage dbManage = new DbManage();
        String sqlTxt = "select * from stu_info where  stu_no='"
                    + stu_no  +"' and stu_pwd = '"
                    + stu_pwd + "'";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //jdbcTemplate.setDataSource(dbManage.getDataSource());
        JdbcTemplate jdbcTemplate =
        (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        if (list.size() > 0)
            flag = true;
        return  flag;
    }
    /*public boolean checkLogin(String sqlTxt){
        boolean flag = false;

        return  flag;
    }*/
}
