package com.seu.udo.domain.executor;

import rx.Scheduler;

/**
 * Author: Jeremy Xu on 2016/4/6 10:45
 * E-mail: jeremy_xm@163.com
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
