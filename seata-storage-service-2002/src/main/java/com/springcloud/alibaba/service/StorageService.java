package com.springcloud.alibaba.service;

public interface StorageService {

    /**
     * 删减库存
     * @param productId 商品id
     * @param count 商品数量
     */
    void decrease( Long productId,Integer count);
}
