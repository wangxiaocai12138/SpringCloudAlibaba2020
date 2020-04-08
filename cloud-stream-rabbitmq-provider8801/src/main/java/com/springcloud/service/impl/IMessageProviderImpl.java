package com.springcloud.service.impl;

import com.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/***
 * 此实现类是与rabbitmq打交道
 * 所以不用@service 注解
 */
//定义消息推送的管道
@EnableBinding(Source.class)  /*指信道channel和exchange绑定在一起*/
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("******serial>>>>"+serial);
        return null;
    }
}
