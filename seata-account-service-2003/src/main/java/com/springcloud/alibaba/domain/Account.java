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
public class Account {
    private Long id;
    private Long userId;//账户id
    private BigDecimal toeal;//总额度
    private BigDecimal used;//已用余额
    private BigDecimal residue;//剩余可用额度
}
