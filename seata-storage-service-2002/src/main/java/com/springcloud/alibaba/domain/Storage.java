package com.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Storage {
    private Long id;
    private Long productId;//产品id
    private Integer total;//总库存
    private Integer used;//已用库存
    private Integer residue;//剩余库存
}
