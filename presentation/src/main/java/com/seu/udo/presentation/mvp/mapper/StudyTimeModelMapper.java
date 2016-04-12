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
        //TODO:
        return null;
    }

    public Collection<StudyTimeModel> transform(Collection<StudyTime> studyTimes) {
        //TODO:
        return null;
    }
}
