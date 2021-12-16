package com.pm.pmapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Oss上传成功后的回调参数
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 10:52
 */
@Getter
@Setter
public class OssCallbackParam {
    private String callbackUrl;
    private String callbackBody;
    private String callbackBodyType;
}
