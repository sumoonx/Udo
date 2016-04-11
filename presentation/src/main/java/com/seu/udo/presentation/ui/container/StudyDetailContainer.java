package com.seu.udo.presentation.ui.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.seu.udo.R;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;
import com.seu.udo.presentation.mvp.presenter.StudyDetailPresenter;
import com.seu.udo.presentation.mvp.view.StudyDetailView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Author: Jeremy Xu on 2016/4/11 10:17
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailContainer extends LinearLayout implements StudyDetailView {

    @Inject StudyDetailPresenter studyDetailPresenter;

    public StudyDetailContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.study_detail_container, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    @Override
    public void renderRank(int rank) {

    }

    @Override
    public void renderStudyTimes(Collection<StudyTimeModel> studyTimeModels) {

    }

    @Override
    public void renderAppUsage() {

    }
}
