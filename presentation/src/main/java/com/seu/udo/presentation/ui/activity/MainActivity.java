/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.seu.udo.R;
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.component.MainComponent;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.ui.view.dispatcher.MainDispatcher;
import com.seu.udo.presentation.ui.view.parceler.BasicKeyParceler;
import com.seu.udo.presentation.ui.view.screen.MainScreen;

import butterknife.OnClick;
import flow.Flow;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

/**
 * Author: Jeremy Xu on 2016/4/5 20:02
 * E-mail: jeremy_xm@163.com
 */
public class MainActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerService.<MainComponent>getDaggerComponent(this);
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        MortarScope mainScope = MortarScope.findChild(getApplicationContext(), getScopeName());

        if (mainScope == null) {
            mainScope = MortarScope.buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, DaggerService.createComponent(MainComponent.class,
                            DaggerService.<ApplicationComponent>getDaggerComponent(getApplicationContext()),
                            new ActivityModule(this)))
                    .build(getScopeName());
        }
        return mainScope.hasService(name) ? mainScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .dispatcher(new MainDispatcher(this))
                .defaultKey(new MainScreen())
                .keyParceler(new BasicKeyParceler())
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.main;
    }

    private String getScopeName() {
        return getClass().getName();
    }
}
