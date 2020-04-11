package com.springcloud.myhandler;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;

/**
 * 自定义流控规则类
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException e){
        return new CommonResult(444,"按客户自定义1限流异常测试ok，global handlerException ---1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"按客户自定义2限流异常测试ok，global handlerException  ---2");
    }
}
