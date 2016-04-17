package com.seu.udo.presentation.internal.di.component;

import com.seu.udo.presentation.internal.di.PerActivity;
import com.seu.udo.presentation.internal.di.module.ActivityModule;
import com.seu.udo.presentation.ui.view.StudyDetailView;
import com.seu.udo.presentation.ui.view.StudyView;

import dagger.Component;

/**
 * Author: Jeremy Xu on 2016/4/11 20:43
 * E-mail: jeremy_xm@163.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface StudyComponent extends ActivityComponent{
    void inject(StudyView studyView);
    void inject(StudyDetailView studyDetailContainer);
}
