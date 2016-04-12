package com.seu.udo.presentation.ui.component;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.seu.udo.R;

import java.text.DecimalFormat;

/**
 * Author: Jeremy Xu on 2016/4/12 20:10
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailMarkerView extends MarkerView {

    private TextView tvContent;

    private DecimalFormat decimalFormat;

    public StudyDetailMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);

        decimalFormat = new DecimalFormat("##.#");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(decimalFormat.format(e.getVal()) + "h"); // set the entry-value as the display text
    }

    @Override
    public int getXOffset(float xpos) {
        // this will center the marker-view horizontally
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset(float ypos) {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
    }
}