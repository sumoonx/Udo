/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.lib.utils.ToastUtil;
import com.seu.udo.presentation.internal.di.component.DaggerStudyComponent;
import com.seu.udo.presentation.internal.di.component.StudyComponent;
import com.seu.udo.presentation.ui.container.StudyDetailContainer;

import butterknife.Bind;

/**
 * Author: Jeremy Xu on 2016/4/5 16:22
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailActivity extends BaseActivity {

    @Bind(R.id.study_detail_container) StudyDetailContainer studyDetailContainer;

    private StudyComponent studyComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, StudyDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initilizeInjector();
        initialStudyDetailContainer();
    }

    @Override
    protected int getLayout() {
        return R.layout.study_detail_main;
    }

    private void initilizeInjector() {
        studyComponent = DaggerStudyComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        LogUtil.i("StudyComponent created here in the StudyDetailActivity.");
    }

    private void initialStudyDetailContainer() {
        studyDetailContainer.inject(studyComponent);
    }
}
