package com.yven.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：yven
 * @date ：Created in 2020/5/26 11:59 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@AllArgsConstructor
@Getter
public class Result<T> {
    /**
     * 返回代码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功时候的调用
     * */
    public static <T> Result<T> success(T data){
        return new  Result<T>(0,"success",data);
    }
    /**
     * 成功时候的调用
     * */
    public static Result success(){
        return new  Result(0,"success",null);
    }

    /**
     * 失败时候的调用
     * */
    public static <T> Result<T> error(CodeMsg codeMsg){
        if(codeMsg == null) {
            return null;
        }
        return new Result<T>(codeMsg.getCode(),codeMsg.getMsg(),null);
    }
}
