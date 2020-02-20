package com.bjpowernode.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.springboot.mapper.UserMapper;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component//spring的注解
@Service//dubbo的注解
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public List<User> getUserByPage(Map<String, Object> paramMap) {
        return userMapper.selectUserByPage(paramMap);
    }

    @Override
    public int getUserByTotal() {
        //设置key的序列化方式，采用字符串方式，可读性较好
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //取redis总数,查看redis中是否有该值
        Integer totalRows = (Integer)redisTemplate.opsForValue().get("totalRows");
        if(totalRows == null){
            //同步
            synchronized (this){
                totalRows = (Integer)redisTemplate.opsForValue().get("totalRows");
                if(totalRows==null){
                    totalRows = userMapper.selectUserByTotal();
                    redisTemplate.opsForValue().set("totalRows",totalRows);
                }
            }
        }
        return userMapper.selectUserByTotal();
    }

    @Override
    public int addUser(User user) {
        //设置key的序列化方式，采用字符串方式，可读性较好
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        int add = userMapper.insertSelective(user);
        if (add > 0){
            //更新缓存数据
            int totalRows = userMapper.selectUserByTotal();
            redisTemplate.opsForValue().set("totalRows",totalRows);
        }

        return add;
    }

    @Override
    public int updataUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        //设置key的序列化方式，采用字符串方式，可读性较好
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        int delete = userMapper.deleteByPrimaryKey(id);
        if (delete > 0){
            //更新缓存数据
            int totalRows = userMapper.selectUserByTotal();
            redisTemplate.opsForValue().set("totalRows",totalRows);
        }
        return delete;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
