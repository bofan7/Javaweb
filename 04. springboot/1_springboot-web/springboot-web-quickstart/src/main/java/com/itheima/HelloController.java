package com.itheima;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //标识当前是一个请求处理类
public class HelloController {

    @RequestMapping("/hello")// 路径映射：当访问 /hello 时，调用这个方法（支持 GET/POST 等）
    public String hello(String name){
        System.out.println("Hello Controller ... hello ： " + name);
        return "Hello " + name + "~";
    }

}
