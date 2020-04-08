package com.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {

    /*以前的方式*/
    // public static final String SERVER_URL="http://nacos-payment-provider";
    /*因为在yml文件中配置过找到服务提供者的*/
    @Value("${service-url.nacos-user-service}")
    private String serverURL; //直接读取配置文件中的微服务提供者名称，完成配置与代码分离
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable Long id){
        return restTemplate.getForObject(serverURL+"/payment/"+id,String.class);
    }

    /*@GetMapping(value = "/consumer/test1")
    public String test1(){
        return restTemplate.getForObject(serverURL+"/test1",String.class);
    }*/
}
