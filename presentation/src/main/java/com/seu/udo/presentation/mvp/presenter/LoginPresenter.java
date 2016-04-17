/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.domain.Credential;
import com.seu.udo.domain.interactor.DoLogin;
import com.seu.udo.domain.response.Response;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.mvp.view.LoginMvpView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Author: Jeremy Xu on 2016/4/5 21:05
 * E-mail: jeremy_xm@163.com
 */
public class LoginPresenter implements MvpPresenter<LoginMvpView> {

    private LoginMvpView loginMvpView;

    private DoLogin doLoginUseCase;

    @Inject
    public LoginPresenter(DoLogin doLogin) {
        LogUtil.i("LoginPresenter injected!");
        doLoginUseCase = doLogin;
    }

    public void doLogin(String account, String password) {
        LogUtil.i("doLogin...");
        if (loginMvpView != null) {
            loginMvpView.showLoading();
        }
        doLoginUseCase.credential(new Credential(account, password))
                .execute(new DoLoginSubscriber());
    }

    @Override
    public void takeView(LoginMvpView view) {
        loginMvpView = view;
    }

    @Override
    public void dropView() {
        loginMvpView = null;
        doLoginUseCase.unsubscribe();
    }

    private final class DoLoginSubscriber extends Subscriber<Response> {
        @Override
        public void onCompleted() {
            if (loginMvpView != null) {
                loginMvpView.hideLoading();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (loginMvpView != null) {
                loginMvpView.hideLoading();
                loginMvpView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(Response response) {
            if (loginMvpView != null) {
                loginMvpView.hideLoading();
                if (response.isSuccess()) {
                    loginMvpView.showSuccess();
                } else {
                    loginMvpView.showError("Login failed, please try again!");
                }
            }
        }
    }
}
