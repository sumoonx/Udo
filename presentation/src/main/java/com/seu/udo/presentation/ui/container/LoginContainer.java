package com.seu.udo.presentation.ui.container;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mrengineer13.snackbar.SnackBar;
import com.joanzapata.iconify.widget.IconTextView;
import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.mvp.presenter.LoginPresenter;
import com.seu.udo.presentation.mvp.view.LoginView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
public class LoginContainer extends LinearLayout implements LoginView {

    /**
     * The callback to communicate with the {@link Activity}.
     * In some sense, it is much like a {@link android.view.View.OnClickListener}
     * for a {@link android.widget.Button}.
     */
    public interface LoginCallback {
        void onLoginSuccess();
    }

    @Bind(R.id.itv_loading) IconTextView iconTextView;
    private SnackBar snackBar;

    @Inject LoginPresenter loginPresenter;
    @Inject Activity activity;
    private LoginCallback loginCallback;

    public LoginContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.login_container, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.bind(this);
        hideError();
        hideLoading();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        ButterKnife.unbind(this);
        loginPresenter.detachView();
        LogUtil.i("LoginContainer detached from loginPresenter.");
    }

    public void inject(LoginComponent loginComponent) {
        loginComponent.inject(this);
        loginPresenter.attachView(this);
        LogUtil.i("LoginContainer attached to loginPresenter.");

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideError();
            }
        });
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
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
        snackBar = new SnackBar.Builder(activity)
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
        loginCallback.onLoginSuccess();
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
}
