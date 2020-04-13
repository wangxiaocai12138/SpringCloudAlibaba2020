package com.springcloud.serivce;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class   )
public interface PaymentService {

    @GetMapping(value = "/payment/{id}") //实际调用地址
    public CommonResult<Payment> payment(@PathVariable("id") Long id);
}
