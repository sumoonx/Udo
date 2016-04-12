package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.mapper.StudyTimeModelMapper;
import com.seu.udo.presentation.mvp.model.AppUsageModel;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;
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

    public void getRank() {
        studyDetailView.renderRank((int)(Math.random() * 2000));
    }

    public void getStudyTimes() {
        List<StudyTimeModel> studyTimeModels = new ArrayList<>();
        studyTimeModels.add(new StudyTimeModel("周三", (float) Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周四", (float)Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周五", (float)Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周六", (float)Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周日", (float)Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周一", (float)Math.random() * 5));
        studyTimeModels.add(new StudyTimeModel("周二", (float)Math.random() * 5));
        studyDetailView.renderStudyTimes(studyTimeModels);
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
