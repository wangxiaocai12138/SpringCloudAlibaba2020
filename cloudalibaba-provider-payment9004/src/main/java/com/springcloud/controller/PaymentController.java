package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    /*模拟数据库*/
    public static HashMap<Long , Payment> hashMap =new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"aaaaaaaaaa"));
        hashMap.put(2L,new Payment(2L,"bbbbbbbbbb"));
        hashMap.put(3L,new Payment(3L,"cccccccccc"));
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSql(@PathVariable Long id){
        Payment payment=hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<>(200,"from  mysql ,serverPort："+serverPort,payment);
        return result;
    }

}
