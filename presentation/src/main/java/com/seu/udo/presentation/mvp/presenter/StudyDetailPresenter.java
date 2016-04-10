package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.view.StudyDetailView;

/**
 * Author: Jeremy Xu on 2016/4/10 13:53
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailPresenter implements MvpPresenter<StudyDetailView> {

    private StudyDetailView studyDetailView;

    @Override
    public void attachView(StudyDetailView studyDetailView) {
        this.studyDetailView = studyDetailView;
    }

    @Override
    public void detachView() {
        studyDetailView = null;
    }
}
