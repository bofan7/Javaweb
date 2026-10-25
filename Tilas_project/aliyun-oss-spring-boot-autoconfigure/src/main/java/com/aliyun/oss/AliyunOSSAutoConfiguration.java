package com.aliyun.oss;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *AliyunOSS的自动配置类
*/
@EnableConfigurationProperties(AliyunOSSProperties.class)
@AutoConfiguration
public class AliyunOSSAutoConfiguration {

    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties){
        return new AliyunOSSOperator(aliyunOSSProperties);

    }
}