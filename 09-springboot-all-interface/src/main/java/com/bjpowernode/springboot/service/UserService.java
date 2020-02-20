package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 分页查询
     * @param paramMap
     * @return
     */
    List<User> getUserByPage(Map<String,Object> paramMap);

    /**
     * 分页查询需要的总数
     * @return
     */
    int getUserByTotal();

    /**
     * 添加用户
     * @param user
     */
    int addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updataUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */

    int deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);
}
