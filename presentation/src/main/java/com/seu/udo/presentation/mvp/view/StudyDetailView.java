package com.seu.udo.presentation.mvp.view;

import java.util.concurrent.TimeUnit;

/**
 * Author: Jeremy Xu on 2016/4/10 13:54
 * E-mail: jeremy_xm@163.com
 */
public interface StudyDetailView extends MvpView {

    void renderRank(int rank);

    void showStudyTime();

    void hideStudyTime();

    void showAppTime();

    void hideAppTime();
}
