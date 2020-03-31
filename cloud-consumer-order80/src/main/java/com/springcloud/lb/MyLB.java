package com.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component //让spring能够扫描的到内容
public class MyLB implements LoadBalancer {


    private AtomicInteger atomicInteger =new AtomicInteger(0);//原子类

    /**
     * 使用自旋锁获得需要访问机器码的次数，由此的到当前第几次访问
     * @return
     */
    public final  int getAndIncrement(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next=current >= 2147483647 ? 0 : current+1;//当下标自加到int类型最大值时要重新开始计数

        }while (!this.atomicInteger.compareAndSet(current,next));//CAS的知识，current是期望值，next是修改值,自旋锁如果一直得不到想要的值就让他一直死循环下去
        System.out.println("***** 第几次访问-  next:"+next+"*****");
        return next;
    }

    /**
     *
     * @param serviceInstances  当前集群集合
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index= getAndIncrement() % serviceInstances.size();//获取下标
        return serviceInstances.get(index);
    }
}
