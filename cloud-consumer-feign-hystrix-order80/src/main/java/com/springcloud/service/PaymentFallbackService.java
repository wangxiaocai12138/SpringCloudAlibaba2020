package com.springcloud.service;


import org.springframework.stereotype.Component;

/**
 * 实现后就相当于为每一个方法添加一个fullback方法
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "- ------ -- PaymentFallbackService  fallback ,(┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "- ------ -- PaymentFallbackService  fallback ,(┬＿┬)";
    }
}
