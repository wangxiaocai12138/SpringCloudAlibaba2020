package com.springcloud;


import com.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient//将此模块注册进eureka注册中心中
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = MySelfRule.class)//name=“修改服务名称” configuration=“完成修改的类”
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
