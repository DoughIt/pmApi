package com.pm.pmapi.common.api;

/**
 * @Description 错误码接口定义
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-12 09:29
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
