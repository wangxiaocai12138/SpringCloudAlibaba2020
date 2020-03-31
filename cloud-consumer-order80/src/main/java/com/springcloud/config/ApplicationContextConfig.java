package com.springcloud.config;

//远程服务调用类（RestTemplate）

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean//添加到spring的容器中
    @LoadBalanced //设置赋予Resttemplate支持负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
