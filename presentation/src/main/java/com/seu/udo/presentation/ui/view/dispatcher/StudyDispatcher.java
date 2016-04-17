package com.seu.udo.presentation.ui.view.dispatcher;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.udo.R;
import com.seu.udo.presentation.ui.view.screen.StudyDetailScreen;
import com.seu.udo.presentation.ui.view.screen.StudyScreen;

import flow.Dispatcher;
import flow.Traversal;
import flow.TraversalCallback;

/**
 * Author: Jeremy Xu on 2016/4/17 18:02
 * E-mail: jeremy_xm@163.com
 */
public class StudyDispatcher implements Dispatcher {

    private final Activity activity;

    public StudyDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(Traversal traversal, TraversalCallback callback) {
        Object dest = traversal.destination.top();

        ViewGroup frame = (ViewGroup) activity.findViewById(R.id.study_frame);

        if (traversal.origin != null) {
            if (frame.getChildCount() > 0) {
                traversal.getState(traversal.origin.top()).save(frame.getChildAt(0));
                frame.removeAllViews();
            }
        }

        @LayoutRes final int layout;
        if (dest instanceof StudyScreen) {
            layout = R.layout.study_screen;
        } else if (dest instanceof StudyDetailScreen) {
            layout = R.layout.study_detail_screen;
        } else {
            throw new AssertionError("Unrecognized screen " + dest);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(dest, activity))
                .inflate(layout, frame, false);

        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted();
    }
}
