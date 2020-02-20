package com.bjpowernode.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class UserController {
    @Reference
    private UserService userService;
    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value = "curPage",required = false) Integer curPage){
        //每页展示10条数据（写死固定）
        int pageSize = 3;
        if (curPage==null || curPage < 1){
            curPage=1;
        }
        //总行数
        int totalRows = userService.getUserByTotal();
        //计算分页数
        int totalPage = totalRows / pageSize;
        //有可能有余数
        int left = totalRows % pageSize;
        if (left>0){
            totalPage = totalPage+1;
        }

        if (curPage>totalPage){
            curPage = totalPage;
        }
        //计算查询的开始行
        int startRow = (curPage - 1) * pageSize;
        Map<String,Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("startRow",startRow);
        paramMap.put("pageSize",pageSize);
        List<User> userList = userService.getUserByPage(paramMap);
        model.addAttribute("userList",userList);
        model.addAttribute("curPage",curPage);
        model.addAttribute("totalPage",totalPage);
        //跳转到模板页面
        return "index";
    }

    /**
     * 去添加用户
     * @return
     */
    @RequestMapping("/user/toAddUser")
    public String toAddUser(){
        return "addUser";
    }


    /**
     * 添加用户或修改用户
     * @return
     */
    @RequestMapping("/user/addUser")
    public String addUser(User user){
        Integer id = user.getId();
        if (id == null){
            //添加用户
            userService.addUser(user);

        }else {
            //修改用户
            userService.updataUser(user);
        }
        return "redirect:/index";
    }


    /**
     * 去修改用户
     * @return
     */
    @RequestMapping("/user/toUpdate")
    public String unpdataUser(Model model,@RequestParam("id") Integer id){

        //添加用户
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "addUser";
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/user/delete")
    public String deteleUser(@RequestParam("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/index";
    }

}
