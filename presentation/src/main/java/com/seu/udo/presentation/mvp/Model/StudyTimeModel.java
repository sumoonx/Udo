package com.seu.udo.presentation.mvp.model;

/**
 * Author: Jeremy Xu on 2016/4/11 10:30
 * E-mail: jeremy_xm@163.com
 */
public class StudyTimeModel {

    private final String day;
    private float hour;

    public StudyTimeModel(String day, float hour) {
        this.day = day;
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public float getHour() {
        return hour;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Study time at " + day + " is: ");
        stringBuilder.append(hour + "hours ");

        return stringBuilder.toString();
    }
}
