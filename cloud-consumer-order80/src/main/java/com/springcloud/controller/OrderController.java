package com.springcloud.controller;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL="http://localhost:8001";CLOUD-PROVIDER-PAYMENT
    public static final String PAYMENT_URL="http://CLOUD-PROVIDER-PAYMENT";//调用对外提供服务的名称，实现负载均衡支持

    //使用RestTemplate调用不同端口的服务调用（spring的）
    @Resource
    private RestTemplate restTemplate;

    @Resource //使用自定义的负载均衡方法
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    /**
     * entity方法
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/entityCreate")
    public CommonResult<Payment> create2(Payment payment){
        ResponseEntity<CommonResult> entity =restTemplate.postForEntity(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"添加错误");
        }
    }



    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

    }

    /**
     * entity 方法
     * @param id
     * @return
     */

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity=restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){//1xx 2xx 3xx 4xx 5xx  请求状态
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }



    //获取微服相关信息
    @GetMapping("/consumer/payment/discovery")
    public Map getPayment(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/discovery",Map.class);

    }

    /**
     * 使用自定义的负载均衡方法
     * @return
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances= discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        if(instances == null || instances.size() <=0){
            return null;
        }
        //调用自定义的负载均衡方法获取选取的端口
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri= serviceInstance.getUri();
        System.out.println("________________=-="+uri.toString());
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
