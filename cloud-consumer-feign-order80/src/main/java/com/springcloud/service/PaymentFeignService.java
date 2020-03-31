package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign接口实现调用provider服务
 */
@Component//让spring能够扫描到此类
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT") //支持feign服务调用（调用的那个服务就写那个服务接口内的方法）
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);//与8001controller中的方法一致

    @GetMapping(value = "/payment/timeout")
    public String timeOut();
}
