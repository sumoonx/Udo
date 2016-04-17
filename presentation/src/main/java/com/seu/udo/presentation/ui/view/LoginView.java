package com.seu.udo.presentation.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.github.mrengineer13.snackbar.SnackBar;
import com.joanzapata.iconify.widget.IconTextView;
import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.mvp.presenter.LoginPresenter;
import com.seu.udo.presentation.mvp.view.LoginMvpView;
import com.seu.udo.presentation.mvp.DaggerService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Implementation of {@link LoginMvpView}.
 * You can add this layout directly into yor layout xml file.
 *
 * Author: Jeremy Xu on 2016/4/10 14:48
 * E-mail: jeremy_xm@163.com
 */
public class LoginView extends BaseView implements LoginMvpView {

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

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialView(context);
    }

    public LoginView(Context context) {
        super(context);
        initialView(context);
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
    }

    @Override
    protected int getLayout() {
        return R.layout.login_screen;
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

    private void initialView(Context context) {
        DaggerService.<LoginComponent>getDaggerComponent(context).inject(this);
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
