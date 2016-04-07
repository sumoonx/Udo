/*
 * Copyright (c) 2016. Team Udo from Southeast University
 * All rights reserved.
 */

package com.seu.udo.presentation.view.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mrengineer13.snackbar.SnackBar;
import com.joanzapata.iconify.widget.IconTextView;
import com.seu.udo.R;
import com.seu.udo.lib.utils.LogUtil;
import com.seu.udo.presentation.internal.di.component.LoginComponent;
import com.seu.udo.presentation.presenter.LoginPresenter;
import com.seu.udo.presentation.view.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Author: Jeremy Xu on 2016/4/5 20:30
 * E-mail: jeremy_xm@163.com
 */
public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    @Inject LoginPresenter loginPresenter;

    @Bind(R.id.bt_login_wechat) FancyButton wechat_login_bt;
    @Bind(R.id.bt_login_facebook) FancyButton facebook_login_bt;
    @Bind(R.id.itv_loading) IconTextView iconTextView;
    @Bind(R.id.login_fragment_layout) LinearLayout linearLayout;
    private SnackBar snackBar;
    private View view;

    private Handler handler;
    private boolean isLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("LoginFragment created here.");
        LoginComponent loginComponent = getComponent(LoginComponent.class);
        if (loginComponent == null) {
            throw new NullPointerException("LoginComponent cant be null");
        }
        LogUtil.i("LoginCompoennt got.");
        loginComponent.inject(this);
        LogUtil.i("LoginComponent injected.");

        loginPresenter.attachView(this);

        handler = new Handler(Looper.getMainLooper());
        isLoading = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);

        ButterKnife.bind(this, view);

        wechat_login_bt.setOnClickListener(this);
        facebook_login_bt.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        hideLoading();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoading() {
        isLoading = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("show loading...");
                if (isLoading) {
                    iconTextView.setVisibility(View.VISIBLE);
                }
            }
        }, 200);
    }

    @Override
    public void hideLoading() {
        isLoading = false;
        LogUtil.i("hide loading...");
        iconTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        LogUtil.i(message);
        snackBar = new SnackBar.Builder(getActivity())
                .withMessage(message)
                .withDuration(SnackBar.SHORT_SNACK)
                .withStyle(SnackBar.Style.INFO)
                .show();
    }

    @Override
    public void showSuccess() {
        LogUtil.i("Success");
        navigator.toMain(getActivity());
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_wechat:
                LogUtil.i("login with wechat...");
                loginPresenter.doLogin("admin", "admin");
                break;
            case R.id.bt_login_facebook:
                LogUtil.i("login with facebook...");
                loginPresenter.doLogin("admin", "123456");
                break;
        }
        if (snackBar != null) {
            snackBar.hide();
        }
    }
}
