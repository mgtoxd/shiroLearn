package pers.mtx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Security;

@Controller
public class UserContrller {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("UserController.hello()");
        return "ok";
    }
    /*
    测试thymeleaf
     */
    @RequestMapping("/testThymeleaf")
    public String testthymeleaf(Model model){
//        数据存入
        model.addAttribute("name","mtx");
//        返回到test.html
        return "test";
    }
    @RequestMapping("/add")
    public String add(Model model){
//        数据存入
        model.addAttribute("name","mtx");
//        返回到test.html
        return "/user/add";
    }
    @RequestMapping("/update")
    public String update(Model model){
//        数据存入
        model.addAttribute("name","mtx");
//        返回到test.html
        return "/user/update";
    }

    @RequestMapping("/tologin")
    public String tologin(Model model){
//        数据存入
        model.addAttribute("name","mtx");
//        返回到test.html
        return "login";
    }
    /*
    登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name,String password,Model model){

        /*
        使用shiro编写认证
        1.获取subject
         */
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            subject.login(token);
            //登陆成功
            //跳转到test
            return "redirect:/testThymeleaf";
        }catch (UnknownAccountException e){
//            e.printStackTrace();
            //登陆失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登陆失败:密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }


    }
}
