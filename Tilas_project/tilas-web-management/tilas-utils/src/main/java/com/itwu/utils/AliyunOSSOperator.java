package com.itwu.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.itwu.pojo.AliyunOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSOperator {
    /**
     * 方法0：硬编码配置。不建议使用为了安全和灵活性，请改从配置文件（如 application.yml）读取
     * private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
     * private String bucketName = "javaweb-wu-david";
     * private String region = "cn-beijing";
     */

    /**
     * 方法一：用@value，一个一个从从application.yml里得到具体的值
     * @Value("${aliyun.oss.endpoint}")
     * private String endpoint;
     * 
     * @Value("${aliyun.oss.bucketName}")
     * private String bucketName;
     * 
     * @Value("${aliyun.oss.region}")
     * private String region;
     */

    //// 法二 注入阿里云 OSS 配置属性类。
    //// 通过 @ConfigurationProperties 批量将 yml 中的配置项 注入到 Java 对象中。
    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content, String originalFilename) throws Exception {
        // 获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        // 生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 使用配置类中的凭证。
        OSS ossClient = new OSSClientBuilder().build(
                aliyunOSSProperties.getEndpoint(),
                aliyunOSSProperties.getAccessKeyId(),
                aliyunOSSProperties.getAccessKeySecret());

        try {
            ossClient.putObject(aliyunOSSProperties.getBucketName(), objectName, new ByteArrayInputStream(content));
        } finally {
            ossClient.shutdown();
        }

        return aliyunOSSProperties.getEndpoint().split("//")[0] + "//" + aliyunOSSProperties.getBucketName() + "."
                + aliyunOSSProperties.getEndpoint().split("//")[1] + "/" + objectName;
    }

}
