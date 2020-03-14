package com.kaixin8848.home.utility.result;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),//成功
    QueryNUll(203,"查无结果"),//查无结果
    ParameterError(205,"参数非法"),//参数非法
    FAIL(400,"失败"),//失败
    NoSend(403,"无需发送"),//无需发送
    UNAUTHORIZED(401,"未认证（签名错误）"),//未认证（签名错误）
    NOT_FOUND(404,"接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),//服务器内部错误

    USER_DOES_NOT_EXIST(20001,"用户不存在！！！"),
    VERIFICATION_CODE_ERROR(20002,"验证码错误！！！"),
    SMS_VERIFICATION_CODE_ERROR(20003,"短信验证码错误！！！"),
    SMS_REPEAT_SENDING(20004,"重复发送失败，请在发送60秒后重试！！！"),
    MANAGEABLE_CANTEEN_IS_FULL(20005,"可管理食堂已满！！！"),
    USER_ALREADY_EXISTS(20006,"用户已存在！！！"),
    ;


    private final int code;
    private final String msg ;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }
    public String msg() {
        return msg;
    }
}
