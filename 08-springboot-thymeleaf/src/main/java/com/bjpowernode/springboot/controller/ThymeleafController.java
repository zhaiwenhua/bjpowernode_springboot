package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

//注意每次都加；控制层
@Controller
public class ThymeleafController {
    //只有/boot/index请求才会返回以下数据
    @RequestMapping("/boot/index")
    public String index(Model model, HttpServletRequest request){
        model.addAttribute("msg","Springboot集成thmeleaf");
        User u = new User();
        u.setId(1);
        u.setNick("zhaiwenhua");
        u.setAdress("shanxi");
        u.setEmail("13465");
        u.setPhone("139");
        model.addAttribute(u);

        List<User> userList = new ArrayList<User>();
        Map<String,Object> userMap = new HashMap<>();
        User[] userArray = new User[10];
        for (int i=0;i<10;i++){
            User user = new User();
            user.setId(i);
            user.setNick("zhaiwenhua"+i);
            user.setAdress("shanxi在"+i);
            user.setEmail("13465在"+i);
            user.setPhone("139在"+i);
            userMap.put(String.valueOf(i),user);
            userList.add(user);
            userArray[i] = user;
        }
        model.addAttribute("userList",userList);
        model.addAttribute("userMap",userMap);
        model.addAttribute("userArray",userArray);
        model.addAttribute("username","zhangsan");
        model.addAttribute("sex","1");
        model.addAttribute("isFlag",true);
        model.addAttribute("userlist",null);

        request.setAttribute("name","peijingpowernode");
        request.getSession().setAttribute("phone","852beijiang+session");

        model.addAttribute("date",new Date());


        model.addAttribute("str","0123456789101112");
        //返回到index页面
        return "index";
    }

}
