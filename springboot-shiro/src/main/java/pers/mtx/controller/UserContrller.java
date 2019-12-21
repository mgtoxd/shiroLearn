package pers.mtx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
