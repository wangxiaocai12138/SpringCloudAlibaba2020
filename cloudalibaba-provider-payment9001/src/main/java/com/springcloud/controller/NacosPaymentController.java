package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosPaymentController {

    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/payment/{id}")
    public String getPayment(@PathVariable Integer id){
        return "Hello  Nacos discovery ,id=" + id+",端口号："+serverPort;
    }

}
