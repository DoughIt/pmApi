package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 获取oss上传文件授权返回结果
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 10:49
 */
@Getter
@Setter
public class OssPolicyResult {
    private String accessKeyId;
    private String policy;
    private String signature;
    private String dir;
    private String host;
    private String callback;
}
