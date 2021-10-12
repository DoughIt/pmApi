package com.pm.pmapi.common.exception;

import com.pm.pmapi.common.api.IErrorCode;

/**
 * @Description 自定义断言处理类，用于抛出各种Api异常
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-12 09:59
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
