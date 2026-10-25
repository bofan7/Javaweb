package com.itheima;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {

    /**
     * 方式一：HttpServletResponse 
是什么：最底层、最原始的 Servlet API。

特点：你要像写 C 语言一样，手动操作“流”。

地位：也就是教学演示用。

结论：不用掌握（除非你要写底层的过滤器/拦截器）。
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        // 1. 设置响应状态码
        response.setStatus(401);
        
        // 2. 设置响应头
        response.setHeader("name", "itheima");
        
        // 3. 设置响应体
        response.getWriter().write("<h1>hello response</h1>");
    }
}
