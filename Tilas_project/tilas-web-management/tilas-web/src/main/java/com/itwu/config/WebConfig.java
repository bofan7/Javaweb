package com.itwu.config;

import com.itwu.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")///表示需要loginCheckIntercepor拦截哪些请求
                .excludePathPatterns("/login");///表示不需要loginCheckIntercepor拦截的请求
        /**
         * 拦截路径配置规则说明 (AntPathMatcher语法):
         *
         * 1. /*
         * - 含义: 当前目录下的一级路径
         * - ✅ 匹配: /depts, /emps, /login
         * - ❌ 不匹配: /depts/1
         *
         * 2. /**
         * - 含义: 任意级路径 (递归匹配所有)
         * - ✅ 匹配: /depts, /depts/1, /depts/1/2
         *
         * 3. /depts/*
         * - 含义: /depts 下的一级子路径
         * - ✅ 匹配: /depts/1
         * - ❌ 不匹配: /depts/1/2, /depts (注意: 不包含自身)
         *
         * 4. /depts/**
         * - 含义: /depts 下的任意级子路径
         * - ✅ 匹配: /depts, /depts/1, /depts/1/2
         * - ❌ 不匹配: /emps/1 (前缀不符)
         */
    }
}
