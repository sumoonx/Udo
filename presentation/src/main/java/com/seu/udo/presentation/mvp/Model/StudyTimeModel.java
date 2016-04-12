package com.seu.udo.presentation.mvp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Jeremy Xu on 2016/4/11 10:30
 * E-mail: jeremy_xm@163.com
 */
public class StudyTimeModel {

    private static final int BRIEF_SIZE = 6;

    private final String day;
    private int rank;
    private float totalHour;
    private List<AppUsageModel> appUsageModels;

    public StudyTimeModel(String day, int rank, List<AppUsageModel> appUsageModels) {
        this.day = day;
        this.rank = rank;
        setAppUsageModels(appUsageModels);
    }

    public StudyTimeModel(String day, List<AppUsageModel> appUsageModels) {
        this(day, 0, appUsageModels);
    }

    public StudyTimeModel(String day) {
        this(day, 0, new ArrayList<AppUsageModel>());
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setAppUsageModels(List<AppUsageModel> appUsageModels) {
        totalHour = 0;
        this.appUsageModels = appUsageModels;
        for (AppUsageModel model :
                appUsageModels) {
            totalHour += model.getHour();
        }
    }

    public void updateAppUsageModelByName(String appName, float hour) {
        AppUsageModel appUsageModel = findAppUsageModelByName(appName);
        if (appUsageModel != null) {
            totalHour += hour - appUsageModel.getHour();
            appUsageModel.setHour(hour);
        } else {
            addAppUsageModel(new AppUsageModel(appName, hour));
        }
    }

    public void updateAppUsageModel(AppUsageModel appUsageModel) {
        updateAppUsageModelByName(appUsageModel.getAppName(), appUsageModel.getHour());
    }

    public void addAppUsageModel(AppUsageModel appUsageModel) {
        if (appUsageModel != null) {
            appUsageModels.add(appUsageModel);
            totalHour += appUsageModel.getHour();
        }
    }

    public List<AppUsageModel> getAppUsageBrief() {
        List<AppUsageModel> models = new ArrayList<>();
        Collections.sort(appUsageModels);

        int i = 0;
        while (i < appUsageModels.size() && i < BRIEF_SIZE) {
            models.add(appUsageModels.get(i++));
        }
        if (i >= BRIEF_SIZE) {
            AppUsageModel other = new AppUsageModel("其他", models.get(BRIEF_SIZE - 1).getHour());
            while (i < appUsageModels.size()) {
                other.addHour(appUsageModels.get(i++).getHour());
            }
            models.remove(BRIEF_SIZE - 1);
            models.add(other);
        }

        return models;
    }

    public List<AppUsageModel> getAppUsageLeft() {
        List<AppUsageModel> models = new ArrayList<>();
        Collections.sort(appUsageModels);

        if (appUsageModels.size() > BRIEF_SIZE) {
            for (int i = BRIEF_SIZE; i < appUsageModels.size(); i++) {
                models.add(appUsageModels.get(i));
            }
        }

        return models;
    }

    public String getDay() {
        return day;
    }

    public int getRank() {
        return rank;
    }

    public float getTotalHour() {
        return totalHour;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** StudyTimeModel *****\n");
        stringBuilder.append("Study time at " + day + " is: ");
        stringBuilder.append(totalHour + "hours\n");
        for (AppUsageModel model :
                appUsageModels) {
            stringBuilder.append(model + "\n");
        }
        stringBuilder.append("**************************\n");

        return stringBuilder.toString();
    }

    private AppUsageModel findAppUsageModelByName(String target) {
        AppUsageModel result = null;
        for (AppUsageModel model :
                appUsageModels) {
            if (model.getAppName().equals(target)) {
                result = model;
                break;
            }
        }
        return result;
    }

    private AppUsageModel findAppUsageModel(AppUsageModel target) {
        String appName = target.getAppName();
        return findAppUsageModelByName(appName);
    }
}
