/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.domain.interactor;

import com.seu.udo.domain.executor.PostExecutionThread;
import com.seu.udo.domain.executor.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case, this interface represents a execution unit
 * for different use cases.
 *
 * Author: Jeremy Xu on 2016/4/5 21:07
 * E-mail: jeremy_xm@163.com
 */
public abstract class UseCase {

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    /**
     * Set thread the execution runs on and
     *
     * @param threadExecutor the thread we do complex work
     * @param postExecutionThread the thread we post the result, usually the UI thread
     */
    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param subscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable()}.
     */
    public void execute(Subscriber subscriber) {
        unsubscribe();
        subscription =  buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
