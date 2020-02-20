package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.service.StudenService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @com.alibaba.dubbo.config.annotation.Reference(version = "1.0.0")
    private StudenService studentService;
    @RequestMapping("/boot/student")
    public Object getStudent(@RequestParam("id") Integer id){
        return studentService.getStudent(id);
    }

    @RequestMapping("/boot/sayHi")
    public Object sayHi(@RequestParam("id") Integer id){
        return studentService.sqyHi("dubbo");
    }

}
