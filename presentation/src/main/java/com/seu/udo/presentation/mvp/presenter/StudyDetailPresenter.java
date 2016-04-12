package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.mapper.StudyTimeModelMapper;
import com.seu.udo.presentation.mvp.model.AppUsageModel;
import com.seu.udo.presentation.mvp.view.StudyDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/4/10 13:53
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailPresenter implements MvpPresenter<StudyDetailView> {

    private StudyDetailView studyDetailView;

    private StudyTimeModelMapper studyTimeModelMapper;

    @Inject
    public StudyDetailPresenter(StudyTimeModelMapper studyTimeModelMapper) {
        this.studyTimeModelMapper = studyTimeModelMapper;
    }

    @Override
    public void attachView(StudyDetailView studyDetailView) {
        this.studyDetailView = studyDetailView;
    }

    @Override
    public void detachView() {
        studyDetailView = null;
    }

    public void getAppUsages() {
        List<AppUsageModel> usageModels = new ArrayList<>();
        usageModels.add(new AppUsageModel("微信", 5));
        usageModels.add(new AppUsageModel("微博", 10));
        usageModels.add(new AppUsageModel("知乎", 15));
        usageModels.add(new AppUsageModel("有道", 20));
        usageModels.add(new AppUsageModel("邮件", 20));
        usageModels.add(new AppUsageModel("优酷", 30));
        studyDetailView.renderAppUsages(usageModels);
    }
}
