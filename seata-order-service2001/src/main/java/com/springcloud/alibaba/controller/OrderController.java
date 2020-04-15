package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.domain.CommonResult;
import com.springcloud.alibaba.domain.Order;
import com.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    @GlobalTransactional(name = "fps_create_order",rollbackFor = Exception.class)//rollbackFor 发生相应异常后回滚
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }

}
