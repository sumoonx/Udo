package com.seu.udo.presentation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.github.mrengineer13.snackbar.SnackBar;
import com.joanzapata.iconify.widget.IconTextView;
import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.MainComponent;
import com.seu.udo.presentation.mvp.presenter.LoginPresenter;
import com.seu.udo.presentation.mvp.view.LoginView;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.ui.view.screen.MainScreen;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import flow.Direction;
import flow.Flow;
import flow.History;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Implementation of {@link LoginView}.
 * You can add this layout directly into yor layout xml file.
 *
 * Author: Jeremy Xu on 2016/4/10 14:48
 * E-mail: jeremy_xm@163.com
 */
public class LoginCustomView extends FrameLayout implements LoginView {

    @Bind(R.id.itv_loading) IconTextView iconTextView;
    private SnackBar snackBar;

    @Inject LoginPresenter loginPresenter;

    public LoginCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        initialView(getContext());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        loginPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loginPresenter.dropView();
        ButterKnife.unbind(this);
    }

    private boolean needShowLogin = false;

    @Override
    public void showLoading() {
        LogUtil.i("Prepare to show loading...");
        needShowLogin = true;

        //delay 200ms to show the loading to fix flash problem when login success.
        Observable.timer(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (needShowLogin) {
                            iconTextView.setVisibility(View.VISIBLE);
                            LogUtil.i("Show loading...");
                        }
                    }
                });
    }

    @Override
    public void hideLoading() {
        LogUtil.i("Hide loading...");
        needShowLogin = false;
        iconTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        LogUtil.i("Show error: " + message);
        snackBar = new SnackBar.Builder(getContext(), this)
                .withMessage(message)
                .withDuration(SnackBar.SHORT_SNACK)
                .withStyle(SnackBar.Style.INFO)
                .show();
    }

    @Override
    public void hideError() {
        LogUtil.i("Hide error...");
        if (snackBar != null) {
            snackBar.hide();
            snackBar = null;
        }
    }

    @Override
    public void showSuccess() {
        LogUtil.i("Login success.");
        Flow flow = Flow.get(this);
        if (!flow.goBack()) {
            flow.replaceTop(new MainScreen(), Direction.REPLACE);
        }
    }

    @OnClick(R.id.bt_login_wechat)
    protected void loginWIthWechat() {
        LogUtil.i("Login with Wechat.");
        loginPresenter.doLogin("admin", "admin");
    }

    @OnClick(R.id.bt_login_facebook)
    protected void loginWithFacebook() {
        LogUtil.i("Login with Facebook.");
        loginPresenter.doLogin("admin", "123456");
    }

    private void initialView(Context context) {
        DaggerService.<MainComponent>getDaggerComponent(context).inject(this);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideError();
            }
        });

        hideError();
        hideLoading();
    }
}
