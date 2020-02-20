package com.bjpowernode.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//Servlet包扫描
@ServletComponentScan(basePackages = {"com.bjpowernode.springboot.servlet","com.bjpowernode.springboot.filter"})
public class Application {
    //启动了springboot程序，启动spring程序，启动内嵌的Tomcat
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
