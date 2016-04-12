package com.seu.udo.presentation.ui.component;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Author: Jeremy Xu on 2016/4/12 20:30
 * E-mail: jeremy_xm@163.com
 */
public class HourFormatter implements ValueFormatter {

    private DecimalFormat decimalFormat;

    public HourFormatter() {
        decimalFormat = new DecimalFormat("##.#");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return decimalFormat.format(value) + "h";
    }
}
