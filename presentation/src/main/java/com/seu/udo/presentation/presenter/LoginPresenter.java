/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.presenter;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.interactor.DoLogin;
import com.seu.udo.domain.response.Response;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.view.LoginView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Author: Jeremy Xu on 2016/4/5 21:05
 * E-mail: jeremy_xm@163.com
 */
public class LoginPresenter implements MvpPreseter<LoginView> {

    private LoginView loginView;

    private DoLogin doLoginUseCase;

    @Inject
    public LoginPresenter(DoLogin doLogin) {
        LogUtil.i("LoginPresenter injected!");
        doLoginUseCase = doLogin;
    }

    public void doLogin(String account, String password) {
        loginView.showLoading();
        doLoginUseCase.credential(new Credential(account, password))
                .execute(new DoLoginSubscriber());
        LogUtil.i("prepare to login");
    }

    @Override
    public void attachView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    private final class DoLoginSubscriber extends Subscriber<Response> {
        @Override
        public void onCompleted() {
            loginView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            loginView.showError(e.getMessage());
        }

        @Override
        public void onNext(Response response) {
            loginView.hideLoading();
            if (response.isSuccess()) {
                loginView.showSuccess();
            } else {
                loginView.showError("Login failed, please try again!");
            }
        }
    }
}
