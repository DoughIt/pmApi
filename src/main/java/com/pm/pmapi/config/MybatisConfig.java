package com.pm.pmapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description Mybatis配置类
 * 扫描mbg自动生成的mapper文件夹、用户自定义dao文件夹
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-12 12:42
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.pm.pmApi.mybatis.mapper", "com.pm.pmApi.dao"})
public class MybatisConfig {
}
