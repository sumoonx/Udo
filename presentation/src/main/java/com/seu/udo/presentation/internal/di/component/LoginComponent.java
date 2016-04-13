package com.seu.udo.presentation.internal.di.component;

import com.seu.udo.presentation.internal.di.PerActivity;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.internal.di.module.LoginModule;
import com.seu.udo.presentation.ui.screen.LoginScreen;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/4/6 15:15
 * E-mail: jeremy_xm@163.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, LoginModule.class})
public interface LoginComponent extends ActivityComponent{
    void inject(LoginScreen loginContainer);
}
