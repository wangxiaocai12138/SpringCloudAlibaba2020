package com.springcloud.alibaba.service.impl;


import com.springcloud.alibaba.dao.OrderDao;
import com.springcloud.alibaba.domain.Order;
import com.springcloud.alibaba.service.AccountService;
import com.springcloud.alibaba.service.OrderService;
import com.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    //订单dao   为什么要用到库存和账户服务？因为创建订单需要减库存，和减账户余额
    @Resource
    private OrderDao orderDao;
    //库存service
    @Resource
    private StorageService storageService;
    //账户service
    @Resource
    private AccountService accountService;

    /**
     * 创建订单-》调用库存服务扣减库存-》调用账户服务扣减账户余额-》修改订单状态
     * @param order
     */
    @Override
    public void create(Order order) {
        log.info("------》开始新建订单！");
        orderDao.create(order);
        log.info("------》订单微服务开始调用库存，执行减库存count方法！");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------》订单微服务开始调用库存，执行减库存方法！end");
        log.info("------》订单微服务开始调用账户，执行扣Money方法！");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------》订单微服务开始调用账户，执行扣Money方法！end");

        //修改订单状态，从0>1 ，1代表完成订单
        log.info("------》修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------》修改订单状态结束");

        log.info("------》下订单结束了!☺");
    }
}
