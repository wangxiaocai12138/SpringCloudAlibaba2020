package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义接口编写轮询算法
 */
public interface LoadBalancer {

    /**
     * 获取所有在注册中心的机器
     * @param serviceInstances
     * @return
     */
    ServiceInstance  instances(List<ServiceInstance> serviceInstances);

}
