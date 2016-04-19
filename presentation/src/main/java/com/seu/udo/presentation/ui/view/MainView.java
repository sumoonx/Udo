package com.seu.udo.presentation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.seu.udo.R;
import com.seu.udo.presentation.internal.di.component.MainComponent;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.mvp.presenter.MainPresenter;
import com.seu.udo.presentation.mvp.view.MainMvpView;
import com.seu.udo.presentation.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Jeremy Xu on 2016/4/19 20:19
 * E-mail: jeremy_xm@163.com
 */
public class MainView extends LinearLayout implements MainMvpView {

    private Context context;
    @Inject MainPresenter mainPresenter;
    @Inject Navigator navigator;

    @Override
    public void showStudy() {
        navigator.toStudy(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        DaggerService.<MainComponent>getDaggerComponent(context).inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mainPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        mainPresenter.dropView();
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.main_study_btn)
    protected void clickToStudy() {
        showStudy();
    }
}
