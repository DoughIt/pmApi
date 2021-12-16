package com.pm.pmapi.service;

import com.pm.pmapi.dto.OssCallbackResult;
import com.pm.pmapi.dto.OssPolicyResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description Oss上传管理Service
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 10:54
 */
public interface OssService {
    /**
     * 通过服务器中转上传文件至oss
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * oss上传策略生成
     * @return
     */
    OssPolicyResult policy();


    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
