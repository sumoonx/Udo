/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.seu.udo.presentation.ui.activity.LoginActivity;
import com.seu.udo.presentation.ui.activity.MainActivity;
import com.seu.udo.presentation.ui.activity.StudyActivity;

import javax.inject.Inject;

/**
 * Navigate through your application.
 * You can register every {@link android.app.Activity}
 * or {@link android.app.Service} in the manifest file,
 * and wrap all the start method here.
 *
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

    public void toStudy(Context context) {
        if (context != null) {
            Intent intent = StudyActivity.getCallingIntent(context);
            context.startActivity(intent);
        }
    }
}