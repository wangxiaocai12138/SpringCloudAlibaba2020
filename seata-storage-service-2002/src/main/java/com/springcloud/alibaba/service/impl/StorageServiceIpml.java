package com.springcloud.alibaba.service.impl;

import com.springcloud.alibaba.dao.StorageDao;
import com.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceIpml implements StorageService {

    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
      log.info("----》》》进入storage中开始删减库存！");
      storageDao.decrease(productId, count);
      log.info("----》》》进入storage中删减库存end~");
    }
}
