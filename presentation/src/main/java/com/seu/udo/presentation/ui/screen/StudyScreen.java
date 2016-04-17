package com.seu.udo.presentation.ui.screen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Jeremy Xu on 2016/4/17 18:16
 * E-mail: jeremy_xm@163.com
 */
public class StudyScreen implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public StudyScreen() {
    }

    protected StudyScreen(Parcel in) {
    }

    public static final Parcelable.Creator<StudyScreen> CREATOR = new Parcelable.Creator<StudyScreen>() {
        @Override
        public StudyScreen createFromParcel(Parcel source) {
            return new StudyScreen(source);
        }

        @Override
        public StudyScreen[] newArray(int size) {
            return new StudyScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof StudyScreen;
    }
}
