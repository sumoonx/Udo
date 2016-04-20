package com.seu.udo.presentation.internal.di.component;

import com.seu.udo.presentation.internal.di.PerActivity;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.internal.di.module.UserModule;
import com.seu.udo.presentation.mvp.view.LoginView;
import com.seu.udo.presentation.ui.view.LoginCustomView;
import com.seu.udo.presentation.ui.view.MainCustomView;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/4/17 20:05
 * E-mail: jeremy_xm@163.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, UserModule.class})
public interface MainComponent extends ActivityComponent {
    void inject(LoginCustomView loginView);
    void inject(MainCustomView mainView);
}
