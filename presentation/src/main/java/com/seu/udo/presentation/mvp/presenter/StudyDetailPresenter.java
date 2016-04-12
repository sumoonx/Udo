package com.seu.udo.presentation.mvp.presenter;

import android.test.suitebuilder.annotation.Suppress;

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
    private List<StudyTimeModel> studyTimeModels;

    @Inject
    public StudyDetailPresenter(StudyTimeModelMapper studyTimeModelMapper) {
        this.studyTimeModelMapper = studyTimeModelMapper;
    }

    @Override
    public void attachView(StudyDetailView studyDetailView) {
        this.studyDetailView = studyDetailView;
        generateAppUsages();
    }

    @Override
    public void detachView() {
        studyDetailView = null;
    }

    public void getRank(String day) {
        StudyTimeModel model = getStudyTime(day);
        studyDetailView.renderRank(model.getRank());
    }

    public void getStudyTimes() {
        studyTimeModels = generateStudyTimes();
        studyDetailView.renderStudyTimes(studyTimeModels);
        studyDetailView.renderRank(studyTimeModels.get(studyTimeModels.size() - 1).getRank());
        studyDetailView.renderAppUsages(studyTimeModels.get(studyTimeModels.size() - 1).getAppUsageBrief());
    }

    public void getAppUsage(String day) {
        List<AppUsageModel> usageModels = getStudyTime(day).getAppUsageBrief();
        studyDetailView.renderAppUsages(usageModels);
    }

    private StudyTimeModel getStudyTime(String day) {
        for (StudyTimeModel model :
                studyTimeModels) {
            if (model.getDay().equals(day)) {
                return model;
            }
        }
        return null;
    }

    private List<StudyTimeModel> generateStudyTimes() {
        List<StudyTimeModel> studyTimeModels = new ArrayList<>();
        studyTimeModels.add(new StudyTimeModel("周三", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周四", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周五", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周六", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周日", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周一", getRandomRank(), generateAppUsages()));
        studyTimeModels.add(new StudyTimeModel("周二", 0, generateAppUsages()));
        return studyTimeModels;
    }

    private List<AppUsageModel> generateAppUsages() {
        List<AppUsageModel> usageModels = new ArrayList<>();
        usageModels.add(new AppUsageModel("微信", getRandomHour()));
        usageModels.add(new AppUsageModel("微博", getRandomHour()));
        usageModels.add(new AppUsageModel("知乎", getRandomHour()));
        usageModels.add(new AppUsageModel("有道", getRandomHour()));
        usageModels.add(new AppUsageModel("邮件", getRandomHour()));
        usageModels.add(new AppUsageModel("优酷", getRandomHour()));
        usageModels.add(new AppUsageModel("土豆", getRandomHour()));
        return usageModels;
    }

    private float getRandomHour() {
        return (float) Math.random() * 2;
    }

    private int getRandomRank() {
        return (int)(Math.random() * 2000);
    }
}
