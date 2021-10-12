package com.pm.pmapi.common.api;

/**
 * @Description 定义各类返回码的值
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-10-12 09:31
 */
public enum ResultCode implements IErrorCode{

    // TODO 继续补充完善其他返回码
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期");

    private long code;
    private String message;

    private ResultCode(long code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
