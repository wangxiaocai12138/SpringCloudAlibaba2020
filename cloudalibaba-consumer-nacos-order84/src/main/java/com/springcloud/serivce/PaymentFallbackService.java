package com.springcloud.serivce;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> payment(Long id) {
        return new CommonResult<>(444,"服务降级返回");
    }
}
