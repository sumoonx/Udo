/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */
package com.seu.udo.presentation.internal.di.component;

import android.app.Activity;
import com.seu.udo.presentation.internal.di.PerActivity;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.internal.di.module.ApplicationModule;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/3/29 21:55
 * E-mail: jeremy_xm@163.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    //Exposed to sub-graph
    Activity activity();
}
