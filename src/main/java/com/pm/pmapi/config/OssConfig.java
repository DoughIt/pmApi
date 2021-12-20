package com.pm.pmapi.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 腾讯云cos连接客户端的配置
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 10:34
 */
@Configuration
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String OSS_ACCESS_KEY_ID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String OSS_ACCESS_KEY_SECRET;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(OSS_ENDPOINT, OSS_ACCESS_KEY_ID, OSS_ACCESS_KEY_SECRET);
    }
}
