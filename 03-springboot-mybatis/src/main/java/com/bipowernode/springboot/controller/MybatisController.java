package com.bipowernode.springboot.controller;

import com.bipowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MybatisController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/boot/students")
    public Object students(){
        //开始查询数据库
        //多线程测试缓存穿透问题
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for (int i=0;i<10000;i++){
            executorService.submit(new Runnable() {
                //线程，该线程调用底层查询所有学生的方法
                @Override
                public void run() {
                    studentService.getAllStudent();
                }
            });

        }

        return studentService.getAllStudent();
    }
    @GetMapping("/boot/updata")
    public Object updata(){
        //开始查询数据库
        return studentService.updata();
    }
}
