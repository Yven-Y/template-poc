package com.yven.poc.exception;

import com.yven.poc.entity.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：yven
 * @date ：Created in 2020/5/27 12:01 AM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@AllArgsConstructor
@Data
public class BizException extends RuntimeException{
    /** 错误码对象 */
    private CodeMsg codeMsg;

    /**
     * @Description 带动态参数的构造方法
     * @Param
     * @return
     */
    public BizException(CodeMsg codeMsg,String... args){
        this.codeMsg = codeMsg.fillArgs(args);
    }
}
