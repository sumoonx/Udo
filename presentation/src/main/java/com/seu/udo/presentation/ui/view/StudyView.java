package com.seu.udo.presentation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.StudyComponent;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.mvp.presenter.StudyPresenter;
import com.seu.udo.presentation.mvp.view.StudyMvpView;
import com.seu.udo.presentation.ui.screen.StudyDetailScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Flow;

/**
 * Author: Jeremy Xu on 2016/4/17 18:16
 * E-mail: jeremy_xm@163.com
 */
public class StudyView extends FrameLayout implements StudyMvpView {

    protected Context context;
    @Inject StudyPresenter studyPresenter;

    public StudyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        DaggerService.<StudyComponent>getDaggerComponent(context).inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        studyPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        studyPresenter.dropView();
        super.onDetachedFromWindow();
    }

    @Override
    public void showDetail() {
        LogUtil.i("show detail");
        Flow.get(this).set(new StudyDetailScreen());
    }

    @OnClick(R.id.start_detail)
    protected void startDetail() {
        LogUtil.i("start detail.");
        studyPresenter.showDetail();
    }
}
