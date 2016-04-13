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
import com.seu.udo.presentation.internal.di.component.ApplicationComponent;
import com.seu.udo.presentation.internal.di.component.StudyComponent;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.ui.screen.StudyDetailScreen;

import butterknife.Bind;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

/**
 * Author: Jeremy Xu on 2016/4/5 16:22
 * E-mail: jeremy_xm@163.com
 */
public class StudyActivity extends BaseActivity {

    @Bind(R.id.study_container) FrameLayout container;

    private StudyDetailScreen studyDetailContainer;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, StudyActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerService.<StudyComponent>getDaggerComponent(this);
        initialStudyDetailContainer();
    }

    @Override
    public Object getSystemService(String name) {
        MortarScope studyScope = MortarScope.findChild(getApplicationContext(), getScopeName());

        if (studyScope == null) {
            studyScope = MortarScope.buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME,
                            DaggerService.createComponent(StudyComponent.class,
                                    DaggerService.<ApplicationComponent>getDaggerComponent(getApplicationContext()),
                                    new ActivityModule(this)))
                    .build(getScopeName());
        }
        return studyScope.hasService(name) ? studyScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected int getLayout() {
        return R.layout.study;
    }

    private void initialStudyDetailContainer() {
        studyDetailContainer = new StudyDetailScreen(this);
        container.addView(studyDetailContainer);
    }

    private String getScopeName() {
        return getClass().getName();
    }
}
