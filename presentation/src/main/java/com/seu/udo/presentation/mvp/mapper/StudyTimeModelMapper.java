package com.seu.udo.presentation.mvp.mapper;

import com.seu.udo.domain.bean.StudyTime;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/4/11 11:11
 * E-mail: jeremy_xm@163.com
 */
public class StudyTimeModelMapper {

    @Inject
    public StudyTimeModelMapper() {}

    /**
     * Transform a {@link StudyTime} into an {@link StudyTimeModel}.
     *
     * @param studyTime Object to be transformed.
     * @return {@link StudyTimeModel}.
     */
    public StudyTimeModel transform(StudyTime studyTime) {
        if (studyTime == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        StudyTimeModel studyTimeModel = new StudyTimeModel(studyTime.getDay());
        studyTimeModel.setHour(studyTime.getHour());
        studyTimeModel.setHour(studyTime.getHour());
        studyTimeModel.setSecond(studyTime.getSecond());

        return studyTimeModel;
    }

    public Collection<StudyTimeModel> transform(Collection<StudyTime> studyTimes) {
        Collection<StudyTimeModel> studyTimeModels;

        if (studyTimes != null && !studyTimes.isEmpty()) {
            studyTimeModels = new ArrayList<>();
            for (StudyTime studyTime : studyTimes) {
                studyTimeModels.add(transform(studyTime));
            }
        } else {
            studyTimeModels = Collections.emptyList();
        }

        return studyTimeModels;
    }
}
