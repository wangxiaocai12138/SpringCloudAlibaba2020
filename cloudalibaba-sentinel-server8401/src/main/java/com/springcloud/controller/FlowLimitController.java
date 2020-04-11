package com.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sun.deploy.security.BlockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        /*try {
            //暂停0.8 秒
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        /*处理线程的名称*/
        log.info(Thread.currentThread().getName()+"线程处理-....testB");
        return "-----testB";
    }

    @GetMapping("/testD")
    public String testD(){
       /* try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD  测试RT");*/
        log.info("testD  异常比例");
        int a=10/0;
        return "-----testD  ---测试RT";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "----------testHotKey  ☺";
    }
    //兜底方法
    public String deal_testHotKey(String p1, String p2 , BlockException ex){
        return "---------deal_testHotKey  ,(┬＿┬)";
    }




    @GetMapping("/test1")
    public String test1(){
       return this.testA();
    }
    @GetMapping("/test2")
    public String test2(){
        return this.testA();
    }

}
