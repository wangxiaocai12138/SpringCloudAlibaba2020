package com.springcloud.alibaba.service.impl;

import com.springcloud.alibaba.dao.AccountDao;
import com.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("---->>>进入account中开始修改账户金额！money="+money+"，userID="+userId);
        /*模拟超时异常*/
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("---->>>进入account中修改账户金额完毕！~");
    }
}
