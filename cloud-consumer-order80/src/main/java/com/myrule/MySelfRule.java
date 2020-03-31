package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon 规则类
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //对应IRule的7中选择方式，要那种选哪种就行
        return new RandomRule();//定义为随机
    }
}
