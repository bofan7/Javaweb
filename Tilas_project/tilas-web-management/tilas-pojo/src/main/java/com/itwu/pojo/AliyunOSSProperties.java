package com.itwu.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data // 一般实体类无脑上一个@data，它可以自动生成一些函数
@Component // 用于Ioc
@ConfigurationProperties(prefix = "aliyun.oss") // 告诉SpringBoot将本类中的属性和配置文件application.yml中的aliyun.oss开头的属性进行绑定
public class AliyunOSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String region;
}