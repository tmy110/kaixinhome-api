package com.kaixin8848.home.utility.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genResult(int code) {
        return new Result().setCode(code);
    }

    public static Result genResult(int code,String message) {
        return new Result().setCode(code).setMessage(message);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }

    public static <T> Result<T> genQueryNullResult() {
        return new Result().setCode(ResultCode.QueryNUll).setMessage("查无结果");
    }

    public static Result genFailResult(String message) {
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }
    public static Result getErrorResult(String message) {
        return new Result().setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(message);
    }
    public static Result getErrorResult() {
        return new Result().setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("系统异常");
    }

    public static Result genNoSendResult(String message) {
        return new Result()
                .setCode(ResultCode.NoSend)
                .setMessage(message);
    }

    public static Result genParameterErrorResult(String message) {
        return new Result().setCode(ResultCode.ParameterError).setMessage(message);
    }
}
