package com.seu.udo.presentation.mvp.model;

/**
 * Author: Jeremy Xu on 2016/4/12 12:47
 * E-mail: jeremy_xm@163.com
 */
public class AppUsageModel implements Comparable{
    private final String appName;
    private float hour;

    public AppUsageModel(String appName, float hour) {
        this.appName = appName;
        this.hour = hour;
    }

    public AppUsageModel(String appName) {
        this(appName, 0);
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public void addHour(float hour) {
        this.hour += hour;
    }

    public String getAppName() {
        return appName;
    }

    public float getHour() {
        return hour;
    }

    @Override
    public int compareTo(Object another) {
        float hour = ((AppUsageModel)another).getHour();
        return hour < this.hour ? -1 : (hour == this.hour ? 0 : 1);
    }

    @Override
    public String toString() {
        return appName + " lasts about " + hour + "s";
    }

}