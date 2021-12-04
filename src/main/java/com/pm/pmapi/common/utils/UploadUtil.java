package com.pm.pmapi.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 上传文件
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-04 13:08
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class UploadUtil {
    /**
     * 存放目录
     */
    @Value("${upload.path}")
    String filePath;

    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param dir           上传目录
     * @return url
     */
    public String uploadFile(MultipartFile multipartFile, String dir) {
        String filerUrl = null;
        try {
            if (multipartFile.isEmpty()) {
                return null;
            }
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 文件名
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String basePath = StrUtil.isNotBlank(dir) ? (dir + "/") : filePath;
            // 新的文件名
            String fileName = title + "_upload" + fileSuffix;
            // 获取文件对象
            File file = new File(basePath, fileName);
            existsFile(file);
            // 完成文件的上传
            multipartFile.transferTo(file);
            // 返回绝对路径
            filerUrl = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filerUrl;
    }


    /**
     * 判断文件是否存在，不存在创建
     */
    private static void existsFile(File file) {
        // 判断文件路径是否存在,不存在新建
        if (!file.getParentFile().exists()) {
            boolean a = file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}