package com.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常信息返回封装对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;//状态码
    private String message;//状态信息
    private  T        data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
