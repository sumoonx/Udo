package com.seu.udo.presentation.mvp.model;

/**
 * Author: Jeremy Xu on 2016/4/11 19:47
 * E-mail: jeremy_xm@163.com
 */
public class AppUsageModel implements Comparable {
    private String appName;
    private int duration;

    public AppUsageModel(String appName, int duration) {
        this.appName = appName;
        this.duration = duration;
    }

    public String getAppName() {
        return appName;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Object another) {
        int duration = ((AppUsageModel)another).getDuration();
        return duration < this.duration ? -1 : (duration == this.duration ? 0 : 1);
    }
}
