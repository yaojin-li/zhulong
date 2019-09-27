package com.demo.zhulong.common.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: Column 扩展注解类
 * --------------------------------------
 * @ClassName: ColumnExtend.java
 * @Date: 2019/9/27 19:08
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
// 保留的环境
@Retention(RetentionPolicy.RUNTIME)
// 注释起作用的位置，此处表示字段声明（包括枚举常量）注解
@Target({ElementType.FIELD})
public @interface ColumnExtend {
    /**
     * 列的名称。
     * @return
     */
    String name() default "";

    /**
     * 是否主键。
     * @return
     */
    boolean isPK() default false;

    /**
     * 是否持久化。
     * @return
     */
    boolean isPersist() default true;

    /**
     * 主键长度（仅用于主键为字符串类型的情况）。
     * @return
     */
    int keySize() default 32;

    /**
     * 格式字符串（目前只支持日期时间类型的格式化字符串）。
     * @return
     */
    String format() default "";
}
