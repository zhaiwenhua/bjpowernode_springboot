package com.bjpowernode.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.springboot.mapper.StudentMapper;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@org.springframework.stereotype.Service
@Component
@Service(version = "1.0.0",timeout = 10000) //dubbo注解 <dubbo:service>
public class UserServiceImpl implements StudenService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String sqyHi(String name) {
        return "Hi,spring boot "+name;
    }

    @Override
    public Student getStudent(int id) {
        //查询数据库
        return studentMapper.selectByPrimaryKey(id);
    }
}
