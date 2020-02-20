package com.bipowernode.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启springboot事务支持
public class Application {
    //快捷键psvm
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
}
