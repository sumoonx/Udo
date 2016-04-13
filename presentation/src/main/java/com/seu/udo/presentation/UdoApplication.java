/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.MeteoconsModule;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;
import com.joanzapata.iconify.fonts.WeathericonsModule;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.mvp.DaggerService;
import com.squareup.leakcanary.LeakCanary;
import com.seu.udo.BuildConfig;
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.module.ApplicationModule;

import mortar.MortarScope;

/**
 * Author: Jeremy Xu on 2016/3/29 21:20
 * E-mail: jeremy_xm@163.com
 */
public class UdoApplication extends Application {
    private MortarScope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeLeakDetection();
        initialIconify();

        LogUtil.i("Udo app onCreated.");
    }

    @Override
    public Object getSystemService(String name) {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME, DaggerService.createComponent(ApplicationComponent.class, new ApplicationModule(this)))
                    .build(getScopeName());
        }
        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
            LogUtil.i("LeakCanary installed.");
        }
    }

    private void initialIconify() {
        Iconify
                .with(new FontAwesomeModule())
                .with(new EntypoModule())
                .with(new TypiconsModule())
                .with(new MaterialModule())
                .with(new MaterialCommunityModule())
                .with(new MeteoconsModule())
                .with(new WeathericonsModule())
                .with(new SimpleLineIconsModule())
                .with(new IoniconsModule());
        LogUtil.i("Iconify initialized.");
    }

    private String getScopeName() {
        return "Root";
    }
}
