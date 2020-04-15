package com.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    /**
     * 减去账户余额
     * @param userId 账户id
     * @param money 金额
     */
    void decrease(@Param("userId") Long userId,
                  @Param("money") BigDecimal money);
}
