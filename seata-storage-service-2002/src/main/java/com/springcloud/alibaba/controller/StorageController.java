package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.domain.CommonResult;
import com.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/storage/decrease")
    public CommonResult decrease(@RequestParam Long productId, @RequestParam Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"库存删减成功！");
    }

}
