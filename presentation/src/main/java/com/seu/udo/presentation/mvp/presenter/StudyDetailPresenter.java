package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.mapper.StudyTimeModelMapper;
import com.seu.udo.presentation.mvp.view.StudyDetailView;

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
}
