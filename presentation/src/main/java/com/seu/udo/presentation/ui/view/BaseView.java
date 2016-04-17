package com.seu.udo.presentation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.seu.udo.presentation.mvp.presenter.MvpPresenter;

import butterknife.ButterKnife;

/**
 * Author: Jeremy Xu on 2016/4/13 16:14
 * E-mail: jeremy_xm@163.com
 */
public abstract class BaseView extends FrameLayout {
    private MvpPresenter presenter;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseView(Context context) {
        super(context);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayout();

    private void init(Context context) {
        LayoutInflater.from(context).inflate(getLayout(), this);
        ButterKnife.bind(this);
    }
}
