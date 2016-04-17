package com.seu.udo.presentation.internal.di.component;

import com.seu.udo.presentation.internal.di.module.ActivityModule;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/4/17 20:05
 * E-mail: jeremy_xm@163.com
 */
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface MainComponent extends ActivityComponent {
}
