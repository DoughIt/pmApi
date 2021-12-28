package com.pm.pmapi.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.pm.pmapi.dto.OssCallbackParam;
import com.pm.pmapi.dto.OssCallbackResult;
import com.pm.pmapi.dto.OssPolicyResult;
import com.pm.pmapi.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description Oss上传管理Service实现类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-16 10:55
 */
@Service
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.policy.expire}")
    private int OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String OSS_CALLBACK;
    @Value("${aliyun.oss.url}")
    private String OSS_URL;
    @Value("${aliyun.oss.dir.prefix}")
    private String OSS_DIR_PREFIX;
    @Value("${aliyun.oss.bucketName}")
    private String OSS_BUCKET_NAME;
    @Value("${aliyun.oss.accessKeyId}")
    private String OSS_ACCESS_KEY_ID;
    @Autowired
    private OSS ossClient;


    /**
     * 通过服务器中转上传文件至cos
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), substring);
            file.transferTo(localFile);
            // 存储目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            fileName = OSS_DIR_PREFIX + sdf.format(new Date());
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(OSS_BUCKET_NAME, fileName, localFile);
            ossClient.putObject(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return OSS_URL + "/" + fileName;
    }

    /**
     * cos上传策略生成
     *
     * @return
     */
    @Override
    public OssPolicyResult policy() {
        OssPolicyResult result = new OssPolicyResult();
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = OSS_DIR_PREFIX + sdf.format(new Date());
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + OSS_EXPIRE * 1000L;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long maxSize = (long) OSS_MAX_SIZE * 1024 * 1024;
        // 回调
        OssCallbackParam callback = new OssCallbackParam();
        callback.setCallbackUrl(OSS_CALLBACK);
        callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCallbackBodyType("application/x-www-form-urlencoded");
        // 提交节点
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes(StandardCharsets.UTF_8));
            // 返回结果
            result.setAccessKeyId(OSS_ACCESS_KEY_ID);
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callbackData);
            result.setHost(OSS_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * cos上传成功回调
     *
     * @param request
     * @return
     */
    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = OSS_URL.concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
