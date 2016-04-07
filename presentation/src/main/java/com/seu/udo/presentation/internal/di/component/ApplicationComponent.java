/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */
package com.seu.udo.presentation.internal.di.component;

import android.content.Context;

import com.seu.udo.domain.executor.PostExecutionThread;
import com.seu.udo.domain.executor.ThreadExecutor;
import com.seu.udo.domain.repository.UserRepository;
import com.seu.udo.presentation.internal.di.module.ApplicationModule;
import com.seu.udo.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/3/29 21:22
 * E-mail: jeremy_xm@163.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graph
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();
}
