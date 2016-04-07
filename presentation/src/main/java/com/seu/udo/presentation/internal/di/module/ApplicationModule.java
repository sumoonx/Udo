/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.internal.di.module;

import android.content.Context;

import com.seu.udo.data.executor.JobExecutor;
import com.seu.udo.data.repository.DefaultUserRepository;
import com.seu.udo.domain.executor.PostExecutionThread;
import com.seu.udo.domain.executor.ThreadExecutor;
import com.seu.udo.domain.repository.UserRepository;
import com.seu.udo.presentation.UIThread;
import com.seu.udo.presentation.UdoApplication;
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Jeremy Xu on 2016/3/29 21:17
 * E-mail: jeremy_xm@163.com
 */
@Module
public class ApplicationModule {
    private final UdoApplication udoApplication;

    public ApplicationModule(UdoApplication application) {
        udoApplication = application;
    }

    @Provides @Singleton
    Context provideContext() {
        return udoApplication;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread proPostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(DefaultUserRepository userRepository) {
        return userRepository;
    }
}
