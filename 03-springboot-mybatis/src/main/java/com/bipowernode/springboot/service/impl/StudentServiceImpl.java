package com.bipowernode.springboot.service.impl;


import com.bipowernode.springboot.mapper.StudentMapper;
import com.bipowernode.springboot.model.Student;
import com.bipowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    //注入
    @Autowired
    private StudentMapper studentMapper;
    //Redis是开源的（BSD许可）内存数据结构存储，
    // 用作数据库，缓存和消息代理。它支持数据结构，
    // 例如 字符串，哈希，列表，集合，带范围查询的排序集合，
    // 位图，超日志，带有半径查询的流空间索引和流。
    //注入springboot自动配置好的RedisTemplate
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    //带有缓存的学生数据查询
    public /*synchronized*/ List<Student> getAllStudent() {
        //初始化字符串序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //高并发条件下(多个人同时访问数据库)，此处有点问题；缓存穿透
        //查缓存
        List<Student> studentList=(List<Student>)redisTemplate.opsForValue().get("allStudents");
        //双重监测锁
        if(null == studentList){
            synchronized (this){
                //this当前对象
                studentList=(List<Student>)redisTemplate.opsForValue().get("allStudents");
                //从redis获取一下
                if(null == studentList){
                    System.out.println("查询的数据库。。。。");
                    //缓存为空。查询一遍数据库
                    studentList = studentMapper.selectAllStudent();
                    //把数据库查询出来的数据，放入redis
                    redisTemplate.opsForValue().set("allStudents",studentList);
                }else{
                    System.out.println("查询的缓存。。。。");
                }
            }
        }else{
            System.out.println("查询的缓存。。。。");
        }


        return studentList;
    }
    //事务
    @Transactional
    //覆盖父类方法
    @Override
    public int updata() {
        Student student = new Student();
        student.setId(1);
        student.setName("李四-updata");
        student.setAge(18);
        int updata = studentMapper.updateByPrimaryKeySelective(student);
        //updata.sout快捷键
        System.out.println("更新结果"+updata);
        //除数不能为0，所以此处会抛出一个运行是的异常，上一步的更新就会回滚
        //去掉注解@Transactional，事务不会回滚，所以数据会更新
        int a = 10 / 0;
        return updata;
    }
}
