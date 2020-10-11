package com.stu_course.controller;

import com.stu_course.entity.Stu_TimeTable;
import com.stu_course.entity.Student;
import com.stu_course.service.SStudent;
import com.stu_course.service_mybatis.S_M_Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import javax.jws.WebParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CStudent_Web {
    @Autowired
    private  SStudent autowiredStudent;
    @Autowired
    private S_M_Student s_m_student;

    @RequestMapping("show_edit_student_autobind")
    public String showEditStudentAutobind(HttpServletRequest request, Model model){
        //1：从网页上的URL取到stu_no，
        String stu_no = request.getParameter("stu_no");
        // 2：根据stu_no通过Service类去取得一个学生的信息。
        Student student = autowiredStudent.getSingleStudent(stu_no);
        //3:通过model把学生信息绑定到对应的网页:show_edit_student_autobind。
        model.addAttribute("student", student);
        return "show_edit_student_autobind";
    }
    @RequestMapping("edit_student_autobind")
    public String editStudentAutobind(@ModelAttribute Student student){
        int i = 0;
        i = autowiredStudent.editStudent(student);
        if (i > 0)
            return "redirect:show_all_stu_info";
        else
            return "redirect:show_edit_student_autobind";
    }
    @RequestMapping("show_insert_student_autobind")
    public String showInsertStudentAutobind(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "show_insert_student_autobind";
    }
    @RequestMapping("insert_student_autobind")
    public String insertStudentAutobind(@ModelAttribute Student student){
        int i = 0;
        i = autowiredStudent.insertStudent(student);
        if (i > 0 )//如果插入成功，则跳转到显示学生信息的页面。
            return "redirect:show_all_stu_info";
        else//如果插入失败，则跳转回要插入的表单页面。
            return "redirect:insert_page_web";
    }
    @RequestMapping("show_insert_student")
    public String showInsertStudent(HttpServletRequest request){
        Student student = (Student)
                request.getSession().getAttribute("a_stu_info");
        if (student == null)
            return "redirect:show_login_web";
        return "show_insert_student";
    }
    @RequestMapping("insert_student")
    public String insertStudent(HttpServletRequest request){
        //1:手工组合，2：动态绑定，一行代码都不用写.下次课有时间演示
        Student student = new Student();
        student.setStu_no(request.getParameter("stu_no"));
        student.setStu_name(request.getParameter("stu_name"));
        student.setStu_phone(request.getParameter("stu_phone"));
        student.setStu_age(Integer.parseInt(request.getParameter("stu_age")));
        student.setStu_sex(request.getParameter("stu_sex"));
        student.setStu_pro(request.getParameter("stu_pro"));
        student.setStu_class(request.getParameter("stu_class"));
        int i = 0;
        i = autowiredStudent.insertStudent(student);
        if (i > 0 )//如果插入成功，则跳转到显示学生信息的页面。
            return "redirect:show_all_stu_info";
        else//如果插入失败，则跳转回要插入的表单页面。
            return "redirect:insert_page_web";
    }
    @RequestMapping("show_timetable_web")
    public String showTimeTableWeb(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Student student = (Student)session.getAttribute("a_stu_info");
        String stu_no = student.getStu_no();
        List<Stu_TimeTable> timeTableList =
                autowiredStudent.getTimeTable(stu_no);
        model.addAttribute("time_table_list", timeTableList);
        String[] weeks = {"周一","周二","周三","周四","周五","周六","周七"};
        int[] lessons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        String[] colorArrays = {"#85B8CF", "#90C652", "#D8AA5A", "#FC9F9D", "#0A9A84", "#61BC69", "#12AEF3", "#E29AAD"};
        model.addAttribute("weeks", weeks);
        model.addAttribute("lessons", lessons);
        model.addAttribute("colorArrays", colorArrays);
        return "show_time_table";
    }
    //数据循环显示在页面上。
    @RequestMapping("show_all_stu_info")
    public String showAllStu_info(HttpServletRequest request, Model model){
        Student student = (Student)
                request.getSession().getAttribute("a_stu_info");
        if (student == null)
            return "redirect:show_login_web";

        List<Student> studentList = null;
        studentList = autowiredStudent.getAllStudent();
        model.addAttribute("stu_list", studentList);
        return "show_stu_info";
    }
    //@RequestMapping("check_login_web") //等价与下面这句话
    @RequestMapping(value = "check_login_web", method = RequestMethod.GET)
    public String checkLoginWeb(HttpServletRequest request, Model model){
        String stuNo = request.getParameter("stu_no");
        String pwd = request.getParameter("stu_pwd");
        System.out.println(stuNo + "...|..." + pwd);
        //1:直接new一个SStudent对象
        //SStudent s_student = new SStudent();
        //Student student_from_new = s_student.checkLoginWeb(stuNo, pwd);
        //2:通过SStudent上的@Service注解和CStudent_Web的@Autowired注解
        //spring自动创建了一个autowiredStudent对象。
        Student student_from_autowired=
                autowiredStudent.checkLoginWeb(stuNo, pwd);
        //if (student_from_autowired.equals(null))可能通不过
        if (student_from_autowired == null){
            return "redirect:show_login_web";
        }
        else {//1:调用getsession是为了把数据存到session里面，
            // 调用model是为了把student_from_autowired传递到main网页里面去。
            request.getSession().setAttribute("a_stu_info", student_from_autowired);
            //把从数据库查询来的数据送到控制器后，通过model传递给对应html页面
            //在html对应页面的名字就叫stu_info
            model.addAttribute("stu_info", student_from_autowired);

            System.out.print("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            return "main";
        }
    }
    @RequestMapping("show_login_web")
    public String showLoginWeb(){
        return "login";
    }

    @RequestMapping("show_stu_info_page")
    public String showStu_infoPage(HttpServletRequest request, Model model){
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String page_number = request.getParameter("page_number");
        String page_size = request.getParameter("page_size");
        List<Student> studentList = s_m_student.getStudentPage(
                field,
                condition,
                Integer.parseInt(page_number),
                Integer.parseInt(page_size)
        );
        model.addAttribute("student_list", studentList);
        model.addAttribute("page_number", page_number);
        model.addAttribute("page_size", page_size);
        model.addAttribute("field", field);
        model.addAttribute("condition", condition);
        return "show_stu_info_page";
    }
    @RequestMapping("delete_student")
    public String deleteStudent(HttpServletRequest request){
        String stu_no = request.getParameter("stu_no");
        int i = s_m_student.deleteStudent(stu_no);
        if (i <= 0)
            return "error";
        else //请同学们把原来的查询条件和分页带过来。自愿
            return "redirect:show_stu_info_page?field=default&condition=&page_number=1&page_size=10";
    }
    @RequestMapping("insert_student_autobind_mybatis")
    public String insertStudentAutobindMybatis(@ModelAttribute Student student){
        int i = 0;
        i = s_m_student.insertStudent(student);
        if (i > 0 )//如果插入成功，则跳转到显示学生信息的分页页面。
            return "redirect:show_stu_info_page?field=default&condition=&page_number=1&page_size=10";
        else//如果插入失败，则跳转回要插入的表单页面。
            return "redirect:自己写请求";
    }
}
