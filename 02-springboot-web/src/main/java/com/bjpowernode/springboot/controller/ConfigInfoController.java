package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfigInfoController {
    @Value("${boot.name}")
    private String name;
    @Value("${boot.location}")
    private String location;
    //将组件注入
    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping("/boot/config")
    public @ResponseBody String config(){

        return name+"---"+location+"=="+configInfo.getName()+"---"+configInfo.getLocation();
    }
    
}
