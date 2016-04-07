package com.seu.udo.presentation.internal.di.module;

import com.seu.udo.domain.interactor.DoLogin;
import com.seu.udo.domain.interactor.UseCase;
import com.seu.udo.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Jeremy Xu on 2016/4/6 15:08
 * E-mail: jeremy_xm@163.com
 */
@Module
public class LoginModule {

    @Provides @PerActivity
    @Named("doLogin") UseCase provideDoLogin(DoLogin doLogin) {
        return doLogin;
    }
}
