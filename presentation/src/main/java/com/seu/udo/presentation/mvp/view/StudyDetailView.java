package com.seu.udo.presentation.mvp.view;

import com.seu.udo.presentation.mvp.model.AppUsageModel;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;

import java.util.Collection;
import java.util.List;

/**
 * Author: Jeremy Xu on 2016/4/10 13:54
 * E-mail: jeremy_xm@163.com
 */
public interface StudyDetailView extends MvpView {

    void renderRank(int rank);

    void renderStudyTimes(Collection<StudyTimeModel> studyTimeModels);

    void renderAppUsages(List<AppUsageModel> appUsageModels);
}
