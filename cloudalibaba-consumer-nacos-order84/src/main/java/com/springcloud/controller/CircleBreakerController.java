package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    /*以前的方式*/
    //public static final String SERVER_URL="http://nacos-payment-provider";
    /*因为在yml文件中配置过找到服务提供者的*/
    @Value("${service-url.nacos-user-service}")
    private String serverURL; //直接读取配置文件中的微服务提供者名称，完成配置与代码分离

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") 什么都不配做（程序会直接报错，并在页面显示）
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //只管java运行时异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //只管sentinel 配置规则违规
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback")
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result= restTemplate.getForObject(serverURL+"/payment/"+id,CommonResult.class,id);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException , 非法参数异常....");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException , 没有对应的id记录，空指针异常");
        }
        return result;
    }
    /*fallback 程序异常兜底方法 handlerFallback*/
    public CommonResult handlerFallback(@PathVariable Long id ,Throwable e){
        Payment payment=new Payment(id,"null");
        return new CommonResult(444,"fallback 程序异常兜底方法 handlerFallback,exception内容"+e.getMessage(),payment);
    }
    /*blockHandler 限流控制兜底方法 blockHandler*/
    public CommonResult blockHandler(@PathVariable Long id ,Throwable e){
        Payment payment=new Payment(id,"null");
        return new CommonResult(445,"blockHandler 限流控制兜底方法 blockHandler,exception内容"+e.getMessage(),payment);
    }


    //》》》》》》》openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/{id}") //实际调用地址
    public CommonResult<Payment> payment(@PathVariable Long id){
        return paymentService.payment(id);
    }


}
