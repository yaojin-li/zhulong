package com.demo.zhulong.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时器测试 不使用时需注释掉 @Scheduled 注解
 *
 * 一个文件中只写一个定时器任务，在 springboot 启动类 Application 文件中添加 @EnableScheduling 自动开启定时器任务
 * 在启动 springboot 后，含有 @Scheduled 的任务将自动执行，可定时操作数据库等。
 *
 * 应用：
 * 1. 计数器，同步数据库或 redis；
 * 2. 数据库定时更新数据状态；
 *
 * fixedRate 说明:
 * @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
 * @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
 * @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次
 *
 * --------------------------------------
 * @ClassName: SchedulerTask.java
 * @Date: 2019/11/2 15:54
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@Component
public class SchedulerTask {
    private int count=0;

//    @Scheduled(cron="*/2 * * * * ?")
    // @Scheduled(fixedRate = 2000)
    private void process(){
        System.out.println("定时器 scheduler task runing  "+(count++));
    }

}
