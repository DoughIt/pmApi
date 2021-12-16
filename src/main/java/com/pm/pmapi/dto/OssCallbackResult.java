package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Oss上传文件的回调结果
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 10:53
 */
@Setter
@Getter
public class OssCallbackResult {
    private String finename;
    private String size;
    private String mimeType;
    private String width;
    private String height;
}
