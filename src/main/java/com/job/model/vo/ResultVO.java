package com.job.model.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 通用响应对象
 */
@Data
public class ResultVO<T> implements Serializable {

    /**
     * 请求状态码
     */
    private Integer status;

    /**
     * 请求状态描述
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private static <T> ResultVO<T> of(HttpStatus status, String message, T data) {
        ResultVO<T> vo = new ResultVO<>();
        vo.setStatus(status.value());
        vo.setMessage(message != null ? message : status.getReasonPhrase());
        vo.setData(data);
        return vo;
    }

    /**
     * 请求成功
     */
    public static <T> ResultVO<T> success() {
        return of(HttpStatus.OK, null, null);
    }

    /**
     * 请求成功，指定响应提示
     */
    public static <T> ResultVO<T> success(String message) {
        return of(HttpStatus.OK, message, null);
    }

    /**
     * 请求成功，指定响应数据
     */
    public static <T> ResultVO<T> success(T data) {
        return of(HttpStatus.OK, null, data);
    }

    /**
     * 请求成功，指定响应提示、响应数据
     */
    public static <T> ResultVO<T> success(String message, T data) {
        return of(HttpStatus.OK, message, data);
    }

    /**
     * 请求失败
     */
    public static <T> ResultVO<T> failure() {
        return of(HttpStatus.BAD_REQUEST, null, null);
    }

    /**
     * 请求失败，指定响应提示
     */
    public static <T> ResultVO<T> failure(String message) {
        return of(HttpStatus.BAD_REQUEST, message, null);
    }

    /**
     * 请求失败，指定响应提示、响应数据
     */
    public static <T> ResultVO<T> failure(String message, T data) {
        return of(HttpStatus.BAD_REQUEST, message, data);
    }
}
