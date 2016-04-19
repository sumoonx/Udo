package com.seu.udo.presentation.ui.view.screen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Jeremy Xu on 2016/4/19 20:11
 * E-mail: jeremy_xm@163.com
 */
public class LoginScreen implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public LoginScreen() {
    }

    protected LoginScreen(Parcel in) {
    }

    public static final Parcelable.Creator<LoginScreen> CREATOR = new Parcelable.Creator<LoginScreen>() {
        @Override
        public LoginScreen createFromParcel(Parcel source) {
            return new LoginScreen(source);
        }

        @Override
        public LoginScreen[] newArray(int size) {
            return new LoginScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof LoginScreen;
    }
}
