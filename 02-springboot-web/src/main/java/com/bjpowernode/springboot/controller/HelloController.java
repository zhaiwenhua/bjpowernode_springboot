package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//“Controller一般指的是MVC架构里的控制层,是对项目里的功能做统一的调度。
@Controller
public class HelloController {
    @RequestMapping("/boot/hello")
    public @ResponseBody String  hellon(){
        return  "Hello Spring Boot";
    }

}
