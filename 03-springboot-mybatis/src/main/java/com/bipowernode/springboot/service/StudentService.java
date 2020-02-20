package com.bipowernode.springboot.service;

import com.bipowernode.springboot.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();
    public int updata();
}
