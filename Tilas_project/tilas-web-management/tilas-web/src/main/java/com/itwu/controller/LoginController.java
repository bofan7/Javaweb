package com.itwu.controller;

import com.itwu.pojo.Emp;
import com.itwu.pojo.LoginInfo;
import com.itwu.pojo.Result;
import com.itwu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 登录controller
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户名 & password", emp);
        LoginInfo info = empService.login(emp);
        if (info != null) {
            // 生成jwt令牌
            java.util.Map<String, Object> claims = new java.util.HashMap<>();
            claims.put("id", info.getId());
            claims.put("username", info.getUsername());
            String jwt = com.itwu.utils.JwtUtils.generateJwt(claims);
            info.setToken(jwt);
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }

}
