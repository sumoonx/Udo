/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.presenter;

import com.seu.udo.presentation.view.MvpView;

/**
 * Author: Jeremy Xu on 2016/4/5 20:54
 * E-mail: jeremy_xm@163.com
 */
public interface MvpPreseter<V extends MvpView> {

    void attachView(V v);

    void onResume();

    void onPause();

    void onDestroy();
}
