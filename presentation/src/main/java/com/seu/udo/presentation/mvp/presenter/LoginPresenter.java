/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.interactor.DoLogin;
import com.seu.udo.domain.response.Response;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.mvp.view.LoginView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Author: Jeremy Xu on 2016/4/5 21:05
 * E-mail: jeremy_xm@163.com
 */
public class LoginPresenter implements MvpPresenter<LoginView> {

    private LoginView loginView;

    private DoLogin doLoginUseCase;

    @Inject
    public LoginPresenter(DoLogin doLogin) {
        LogUtil.i("LoginPresenter injected!");
        doLoginUseCase = doLogin;
    }

    public void doLogin(String account, String password) {
        LogUtil.i("doLogin...");
        if (loginView != null) {
            loginView.showLoading();
        }
        doLoginUseCase.credential(new Credential(account, password))
                .execute(new DoLoginSubscriber());
    }

    @Override
    public void attachView(LoginView view) {
        loginView = view;
    }

    @Override
    public void detachView() {
        loginView = null;
        doLoginUseCase.unsubscribe();
    }

    private final class DoLoginSubscriber extends Subscriber<Response> {
        @Override
        public void onCompleted() {
            if (loginView != null) {
                loginView.hideLoading();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (loginView != null) {
                loginView.hideLoading();
                loginView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(Response response) {
            if (loginView != null) {
                loginView.hideLoading();
                if (response.isSuccess()) {
                    loginView.showSuccess();
                } else {
                    loginView.showError("Login failed, please try again!");
                }
            }
        }
    }
}
