/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;

import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.UdoApplication;
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Author: Jeremy Xu on 2016/3/29 21:27
 * E-mail: jeremy_xm@163.com
 */
public abstract class BaseActivity extends Activity {
    @Inject Navigator navigator;

    private ActivityModule activityModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * Add a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection
     *
     * @return A {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((UdoApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return activityModule;
    }

    private void initializeInjector() {
        getApplicationComponent().inject(this);
        LogUtil.i("ApplicationComponent injected.");
        ButterKnife.bind(this);
        activityModule = new ActivityModule(this);
        LogUtil.i("activityModule created.");
    }
}