package com.itwu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwu.pojo.Result;
import com.itwu.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    // prehandle用于校验请求，判断是否放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1. 获取请求url
        String url = request.getRequestURL().toString();
        log.info("拦截到请求的url: {}", url);
        if (url.contains("login")) {
            return true;
        }
        // 2. 获取请求头中的令牌（token）
        String jwt = request.getHeader("token");

        // 3. 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = new ObjectMapper().writeValueAsString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 4. 解析token，如果解析失败，返回错误结果（未登录）
        try {
            io.jsonwebtoken.Claims claims = JwtUtils.parseJwt(jwt);
            Integer empId = (Integer) claims.get("id");
            com.itwu.utils.BaseContext.setCurrentId(empId);
        } catch (Exception e) {
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = new ObjectMapper().writeValueAsString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // 5. 放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        com.itwu.utils.BaseContext.removeCurrentId();
    }
}
