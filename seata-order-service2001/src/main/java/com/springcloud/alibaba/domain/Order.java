package com.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * 异常信息返回封装对象
 * lombok工具包
 * @Data 使用这个注解，就不用再去手写Getter,Setter,equals,canEqual,hasCode,toString等方法了，注解后在编译时会自动加进去
 * @AllArgsConstructor 使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
 * @NoArgsConstructor 使用后创建一个无参构造函数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;//订单状态：0-创建中，1-已完结
}
