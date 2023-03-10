package com.example.base.并发编程.线程池实现示例;

import java.util.function.Supplier;

public interface ThreadPool<Job extends Supplier> {

    // 执行一个job 这个job需要实现runnable接口
    void execute(Job job);
    // 关闭线程池
    void shutdown();
    // 增加工作者线程
    void addWorkers(int num);
    // 减少工作者线程
    void removeWorker(int num);
    // 得到正在等待执行的任务数量
    int getJobSize();

}
