package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池： "+Thread.currentThread().getName()+"  paymentInfo_OK , id:"+id;
    }

    /**
     * 模拟发生异常情况
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //3秒内是正常业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int time=2;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+"  paymentInfo_TimeOut , id:"+id +"耗时："+time+"秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "系统繁忙，请稍后在试，线程池： "+Thread.currentThread().getName()+"  paymentInfo_TimeOutHandler,  id = "+id+"，发生超时~~T T ";
    }


    //====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("******  id 不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();/* hutool.cn */

        return Thread.currentThread().getName()+"\t调用成功，流水号："+serialNumber;
    }

    /*服务熔断后降级的方法*/
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "Id 不能为负数，请稍后在试，(┬＿┬) ~~ id:"+id;
    }

}
