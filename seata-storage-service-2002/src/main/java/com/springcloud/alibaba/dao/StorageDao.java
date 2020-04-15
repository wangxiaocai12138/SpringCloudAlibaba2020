package com.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface StorageDao {
    /**
     * 删减库存
     * @param productId 商品id
     * @param count 商品数量
     */
    void decrease(@Param("productId") Long productId,
                  @Param("count") Integer count);
}
