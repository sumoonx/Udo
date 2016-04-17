package com.seu.udo.presentation.ui.screen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: Jeremy Xu on 2016/4/17 18:12
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailScreen implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public StudyDetailScreen() {
    }

    protected StudyDetailScreen(Parcel in) {
    }

    public static final Parcelable.Creator<StudyDetailScreen> CREATOR = new Parcelable.Creator<StudyDetailScreen>() {
        @Override
        public StudyDetailScreen createFromParcel(Parcel source) {
            return new StudyDetailScreen(source);
        }

        @Override
        public StudyDetailScreen[] newArray(int size) {
            return new StudyDetailScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof StudyDetailScreen;
    }
}
