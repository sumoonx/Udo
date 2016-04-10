/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.HasComponent;
import com.seu.udo.presentation.internal.di.component.DaggerLoginComponent;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.internal.di.module.LoginModule;
import com.seu.udo.presentation.view.fragment.LoginFragment;

/**
 * Author: Jeremy Xu on 2016/4/5 20:01
 * E-mail: jeremy_xm@163.com
 */
public class LoginActivity extends BaseActivity implements HasComponent<LoginComponent>, OnClickListener{

    private LoginComponent loginComponent;

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
        addFragment(R.id.login_fragment_container, new LoginFragment());
        LogUtil.i("LoginFragment added to the LoginActivity.");
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public LoginComponent getComponent() {
        return loginComponent;
    }

    private void initializeInjector() {
        loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .loginModule(new LoginModule())
                .build();
        LogUtil.i("LoginComponent created here in the LoginActivity.");
    }
}
