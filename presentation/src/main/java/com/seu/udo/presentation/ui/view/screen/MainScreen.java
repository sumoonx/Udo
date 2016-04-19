package com.seu.udo.presentation.ui.view.screen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Jeremy Xu on 2016/4/19 20:09
 * E-mail: jeremy_xm@163.com
 */
public class MainScreen implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public MainScreen() {
    }

    protected MainScreen(Parcel in) {
    }

    public static final Parcelable.Creator<MainScreen> CREATOR = new Parcelable.Creator<MainScreen>() {
        @Override
        public MainScreen createFromParcel(Parcel source) {
            return new MainScreen(source);
        }

        @Override
        public MainScreen[] newArray(int size) {
            return new MainScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof MainScreen;
    }
}
