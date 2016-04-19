/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.mvp.view;

/**
 * Author: Jeremy Xu on 2016/4/5 20:26
 * E-mail: jeremy_xm@163.com
 */
public interface LoginView extends MvpView{

    void showLoading();

    void hideLoading();

    void showError(String message);

    void hideError();

    void showSuccess();
}
