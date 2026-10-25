package com.itheima;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class RequestController {


        @RequestMapping("/req")//这个req只是个名字
        public String requestMethodName(HttpServletRequest req) {
            //通常在 Spring Boot 中，我们会直接把参数写在方法里（比如 @RequestParam String name），
            //但这个例子展示的是更底层的做法——直接拿到原始的请求对象（Request Object），然后从中提取数据。
            // 1. 获取请求方式
        String method = req.getMethod();
        System.out.println("请求方式: " + method);

        // 2. 获取请求URL (统一资源定位符)
        StringBuffer url = req.getRequestURL();
        System.out.println("请求URL: " + url);

        // 3. 获取请求协议
        String protocol = req.getProtocol();
        System.out.println("请求协议: " + protocol);

        // 4. 获取请求参数 - name
        String name = req.getParameter("name");
        System.out.println("请求参数name: " + name);

        // 5. 获取请求头 - Accept
        String header = req.getHeader("Accept");
        System.out.println("请求头Accept: " + header);
            return "OK";
        }
        
}
