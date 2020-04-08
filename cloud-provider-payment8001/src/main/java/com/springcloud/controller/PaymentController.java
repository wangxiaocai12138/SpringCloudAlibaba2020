package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource   //服务发现client端(获取相应的服务信息)
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult result(@RequestBody Payment payment){
        int result= paymentService.create(payment);
        log.info("******插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据成功:端口："+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment= paymentService.getPaymentById(id);
        log.info("******查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,端口："+serverPort,payment);
        }else{
            return new CommonResult(444,"未查找到对应记录，查询id:"+id,null);
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Map<Object,Object> discovery(){
        Map<Object,Object> map=new HashMap<>();
        List<String> services=discoveryClient.getServices();
        for (String element : services){
            log.info("*获取微服务名称列表*** element ="+element);
            //获取相应服务下的微服相关信息存入mapper
            List<ServiceInstance> instances = discoveryClient.getInstances(element);
            List<String> list=new ArrayList<>();
            for (ServiceInstance instance : instances){
                String info="服务名称："+instance.getServiceId()+"\n主机ip:"+instance.getHost()+"\n端口号:"+instance.getPort()+"\nip地址+端口:"+instance.getUri();
                log.info("获取相应服务名称下对应的微服相关信息"+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
                list.add(info);
            }
            map.put("注册入Erueka中的服务:"+element,list);
        }
        map.put("相应服务信息:",this.discoveryClient);
        return map ;
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;//调用方法返回端口
    }


    @GetMapping(value = "/payment/timeout")
    public String timeOut(){
        //暂停时间几秒，然后在执行
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     * 测试分布式链路追踪
     * @return
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return  "hi , i am paymentzipkin server fall back,😄~~";
    }

}


