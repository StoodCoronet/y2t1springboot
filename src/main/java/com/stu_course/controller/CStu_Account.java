package com.stu_course.controller;

import com.stu_course.entity.Student;
import com.stu_course.service_mybatis.S_M_Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/stu_account")
public class CStu_Account {
    @Autowired
    private S_M_Account s_m_account;
    @RequestMapping(value = "show_deposit_stu_account")
    public String showDepositStuAccount(){
        return "deposit_stu_account";
    }
    @RequestMapping("deposit_stu_account")
    public String depositStuAccount(HttpServletRequest request){
        //传递过来2个参数，一个money，一个是支付方式，学号从session里面读取
        //在单兴龙基础上写的，写死了学号，请同学们修改成可以给任意同学充值
        String payMethod = request.getParameter("payMethod");
        double deposit_money = Double.parseDouble(
                request.getParameter("money"));
        String stu_no = ((Student)request.getSession()
                .getAttribute("a_stu_info")).getStu_no();
        int i = s_m_account.depositAccount(stu_no, payMethod, deposit_money);
        if (i > 0) //跳转到充值成功界面或者显示帐户余额界面
            return "";
        else //失败则跳转到失败页面
            return "error";
    }
}
