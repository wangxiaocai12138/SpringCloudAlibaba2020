package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")//设置默认的全局fallback
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * 正常访问
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //3秒内是正常业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    //@HystrixCommand  //这样设置代表默认访问 全局的fallback
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfo_OK(id);
        log.info("****** result："+result);
        return result;
    }

    /**
     * 模拟发生异常情况
     * @param id
     * @return
     */

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //3秒内是正常业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "300000")
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result=paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("****** result："+result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "系统繁忙，我是消费者80，请稍后在试，线程池： "+Thread.currentThread().getName()+"  paymentInfo_TimeOutHandler,  id = "+id+"，发生超时~~T T ";
    }

    /**
     * 下面是全局fallback 方法
     * @return
     */
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理信息，请稍后再试，(┬＿┬)~";
    }

}
