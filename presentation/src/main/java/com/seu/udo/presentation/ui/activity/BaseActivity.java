/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Base class for all {@link Activity}s in this app.
 * We introduce {@link Navigator} and {@link ActivityModule} here.
 * You can use {@link ButterKnife} and Dagger2 in any sub-class.
 *
 * You must always override getLayout method to set your layout,
 * otherwise the Activity will have nothing to display!
 *
 * Author: Jeremy Xu on 2016/3/29 21:27
 * E-mail: jeremy_xm@163.com
 */
public abstract class BaseActivity extends Activity {
    @Inject Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());

        ButterKnife.bind(this);
        DaggerService.<ApplicationComponent>getDaggerComponent(getApplicationContext()).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * Override this method to provide your own layout.
     *
     * @return layout resource id
     */
    protected abstract int getLayout();

    /**
     * Get the Main Application component for dependency injection
     *
     * @return A {@link ApplicationComponent}
     */
}
