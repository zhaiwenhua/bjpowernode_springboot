package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFullController {
    //http://localhost:9090/02-springboot-web/boot/user/106
    //{id}和{user}可以放在任何位置
    //http://localhost:9090/02-springboot-web/boot/user/106/张三
    //@RequestMapping("/boot/user/{id}/{name}")
    //http://localhost:9090/02-springboot-web/boot/张三/user/106
    //@RequestMapping("/boot/{name}/user/{id}")
    //http://localhost:9090/02-springboot-web/boot/user/106
    @RequestMapping("/boot/user/{id}")
    public Object user(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setName("张三丰");
        return user;

    }

    //user_id_age和user_age_id有歧义不能同时存在
    @RequestMapping("/boot/user/{id}/{age}")
    public Object user_id_age(@PathVariable("id") Integer id,@PathVariable("age") Integer age){
        User user = new User();
        user.setId(id);
        //user.setName("张三丰");
        return user;

    }
//    @RequestMapping("/boot/user/{age}/{id}")
//    public Object user_age_id(@PathVariable("id") Integer id,@PathVariable("age") Integer age){
//        User user = new User();
//        user.setId(id);
//        //user.setName("张三丰");
//        return user;
//
//    }
}
