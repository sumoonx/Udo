/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.internal.di.module.LoginModule;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.ui.view.LoginCustomView;

import butterknife.Bind;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

/**
 * Author: Jeremy Xu on 2016/4/5 20:01
 * E-mail: jeremy_xm@163.com
 */
public class LoginActivity extends BaseActivity {
    private LoginCustomView loginContainer;

    @Bind(R.id.login_container) FrameLayout linearLayout;

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

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        initialLoginContainer();
    }

    @Override
    public Object getSystemService(String name) {
        MortarScope loginScope = MortarScope.findChild(getApplicationContext(), getScopeName());

        if (loginScope == null) {
            loginScope = MortarScope.buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME,
                            DaggerService.createComponent(LoginComponent.class,
                                    DaggerService.<ApplicationComponent>getDaggerComponent(getApplicationContext()),
                                    new ActivityModule(this), new LoginModule()))
                    .build(getScopeName());
        }
        return loginScope.hasService(name) ? loginScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = MortarScope.findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }

        super.onDestroy();
    }

    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    private void initialLoginContainer() {
        //loginContainer.inject(loginComponent);
        loginContainer = new LoginCustomView(this);
        linearLayout.addView(loginContainer);
        LogUtil.ai("addView loginContainer");
        loginContainer.setLoginCallback(new LoginCustomView.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                navigator.toMain(LoginActivity.this);
                finish();
            }
        });
    }

    private String getScopeName() {
        return getClass().getName();
    }

}
