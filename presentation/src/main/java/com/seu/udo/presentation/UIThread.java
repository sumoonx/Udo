package com.seu.udo.presentation;

import com.seu.udo.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author: Jeremy Xu on 2016/4/6 11:55
 * E-mail: jeremy_xm@163.com
 */
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
