/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.HasComponent;
import com.seu.udo.presentation.internal.di.component.DaggerLoginComponent;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.internal.di.module.LoginModule;
import com.seu.udo.presentation.ui.container.LoginContainer;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Jeremy Xu on 2016/4/5 20:01
 * E-mail: jeremy_xm@163.com
 */
public class LoginActivity extends BaseActivity {

    private LoginComponent loginComponent;

    @Bind(R.id.login_container) LoginContainer loginContainer;

    /**
     * Get a calling {@link Intent}, you should always use this {@link Intent}
     * to start this {@link android.app.Activity}.
     *
     * @param context the context which is going to start this Activity.
     * @return the Intent to start.
     */
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_main);

        initializeInjector();
        initialLoginContainer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
        LogUtil.i("ButterKnife unbind from the LoginActivity.");
    }

    private void initializeInjector() {
        ButterKnife.bind(this);
        LogUtil.i("ButterKnife bind to the LoginActivity.");

        loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .loginModule(new LoginModule())
                .build();
        LogUtil.i("LoginComponent created here in the LoginActivity.");
    }

    private void initialLoginContainer() {
        loginContainer.inject(loginComponent);
        loginContainer.setLoginCallback(new LoginContainer.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                navigator.toMain(LoginActivity.this);
                finish();
            }
        });
    }
}
