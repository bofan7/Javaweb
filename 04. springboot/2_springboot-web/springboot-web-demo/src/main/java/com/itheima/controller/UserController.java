package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息Controller
 */
@RestController //@Controller + @ResponseBody -----> 如果返回的是一个对象/集合 --> 转json --> 响应
public class UserController {

    @Autowired//自动装配: 应用程序在运行时, 会自动的从容器中找到该类型的对象, 并赋值给该变量
    private UserService userService;

    //方式一: 属性注入
    //@Autowired
    //private UserService userService;

    //方式二: 构造器注入
    //private final UserService userService;
    ////@Autowired ---> 如果当前类中只存在一个构造函数, @Autowired可以省略
    //public UserController(UserService userService) {
    //    this.userService = userService;
    //}

    //方式三: setter注入
    //private UserService userService;
    //@Autowired
    //public void setUserService(UserService userService) {
    //    this.userService = userService;
    //}

    //@Qualifier("userServiceImpl")
    //@Autowired
    //private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        //1. 调用service
        List<User> userList = userService.list();
        //2. 响应数据
        return userList;
    }
}

/**如果不分层解耦，则用一下代码一次性完成
 * @RequestMapping("/list")
public List<User> list() {
    
    //1.加载并读取文件
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
    ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
    
    //2.解析数据,封装成对象 ---> 集合
    List<User> userList = lines.stream().map(line -> {
        String[] parts = line.split(",");
        Integer id = Integer.parseInt(parts[0]);
        String username = parts[1];
        String password = parts[2];
        String name = parts[3];
        Integer age = Integer.parseInt(parts[4]);
        LocalDateTime uTime = LocalDateTime.parse(parts[5], 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return new User(id, username, password, name, age, uTime);
    }).collect(Collectors.toList());
    
    //3.响应数据
    return userList;
}
*/