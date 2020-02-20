package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.User;
import org.springframework.web.bind.annotation.*;

@RestController//@RestController=@Controller+@ResponseBody
public class MVCController {
    @RequestMapping(value = "/boot/getUser",method = RequestMethod.GET)
    public Object getUser(){
        User user = new User();
        user.setId(100);
        user.setName("张无忌");
        return user;
    }

    /**
     * @GetMapping 只支持get请求 = @RequestMapping+RequestMethod.GET
     * @return
     */
    //和上面的等价
    @GetMapping(value = "/boot/getUser1")
    public Object getUser1(){
        User user = new User();
        user.setId(100);
        user.setName("张无忌");
        return user;
    }


    /**
     * @PostMapping 只支持post请求 = @RequestMapping+RequestMethod.Post
     * @return
     */
    //和上面的等价
    @PostMapping(value = "/boot/getUser2")
    public Object getUser2(){
        User user = new User();
        user.setId(100);
        user.setName("张无忌-post");
        return user;
    }

}
