/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.seu.udo.presentation.view.activity.BaseActivity;
import com.seu.udo.presentation.view.activity.LoginActivity;
import com.seu.udo.presentation.view.activity.MainActivity;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/3/29 21:28
 * E-mail: jeremy_xm@163.com
 */
public class Navigator {

    @Inject
    public Navigator() {}

    public void toLogin(Context context) {
        if (context != null) {
            Intent intent = LoginActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }

    public void toMain(Context context) {
        if (context != null) {
            Intent intent = MainActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }
}
