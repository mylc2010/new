package com.thymeleaf.view.controller;

import com.thymeleaf.view.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * 跳转登录页
     *
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {
        if ("liu".equals(user.getUserName()) && StringUtils.hasText(user.getPassWord())) {

            session.setAttribute("loginuser", user);
            //重定向到main,登录成功
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "账号密码错误！请重新输入");
            return "login";
        }

    }

    /**
     * 重定向到main页面  防止重复提交表单问题
     *
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model) {
        if (session.getAttribute("loginuser") != null){
            return "index";
        }else {
            model.addAttribute("msg", "尚未登录，请登录！");
            return "login";
        }

    }
}
