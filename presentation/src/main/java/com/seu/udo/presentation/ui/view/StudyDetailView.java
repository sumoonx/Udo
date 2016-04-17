package com.seu.udo.presentation.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.seu.udo.R;
import com.seu.udo.presentation.internal.di.component.StudyComponent;
import com.seu.udo.presentation.mvp.model.AppUsageModel;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;
import com.seu.udo.presentation.mvp.presenter.StudyDetailPresenter;
import com.seu.udo.presentation.mvp.view.StudyDetailMvpView;
import com.seu.udo.presentation.mvp.DaggerService;
import com.seu.udo.presentation.ui.component.HourFormatter;
import com.seu.udo.presentation.ui.component.StudyDetailMarkerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: Jeremy Xu on 2016/4/11 10:17
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailView extends LinearLayout implements StudyDetailMvpView {

    private static final int LINE_CHART_ANITIME = 1500;
    private static final int PIE_CHART_ANITIME_X = 1500;
    private static final int PIE_CHART_ANITIME_Y = 1500;

    private static final int GRID_COLOR = Color.WHITE;
    private static final float GRID_WIDTH = 1f;
    private static final int LINE_COLOR = Color.WHITE;
    private static final float LINE_WIDTH = 2f;

    @Inject StudyDetailPresenter studyDetailPresenter;
    private Context context;

    @Bind(R.id.tv_study_rank) TextView rankTextView;
    @Bind(R.id.lc_study_rank) LineChart lineChart;
    @Bind(R.id.pc_app_while_study) PieChart pieChart;

    public StudyDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        DaggerService.<StudyComponent>getDaggerComponent(context).inject(this);
    }

    /**
     * Lifecycle.
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        studyDetailPresenter.takeView(this);
        setupLineChart();
        setupPieChart();
        studyDetailPresenter.getStudyTimes();
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        studyDetailPresenter.dropView();
        super.onDetachedFromWindow();
    }

    /**
     * Implementation of StudyDetailMvpView.
     * @param rank the rank to render
     */
    @Override
    public void renderRank(int rank) {
        if (rank != 0) {
            rankTextView.setText(String.valueOf(rank));
        } else {
            rankTextView.setText("尚未产生");
        }
    }

    @Override
    public void renderStudyTimes(List<StudyTimeModel> studyTimeModels) {
        lineChart.setData(getLineData(studyTimeModels));
        highlightLast();

        //fix y-axis size
        float min = Collections.min(studyTimeModels).getTotalHour();
        float max = Collections.max(studyTimeModels).getTotalHour();
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.calcMinMax(min, max);

        lineChart.setEnabled(true);
        lineChart.animateX(LINE_CHART_ANITIME);
    }

    @Override
    public void renderAppUsages(List<AppUsageModel> appUsageModels) {
        PieData pieData = getPieData(appUsageModels);

        pieChart.setData(pieData);
        pieChart.setEnabled(true);
        pieChart.animateXY(PIE_CHART_ANITIME_X, PIE_CHART_ANITIME_Y);
    }

    /**
     * Local methods.
     */
    private void setupLineChart() {
        lineChart.setDrawBorders(true);    //hide border
        lineChart.setBorderColor(GRID_COLOR);
        lineChart.setBorderWidth(GRID_WIDTH);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription("");       //hide description
        lineChart.getLegend().setEnabled(false);    //hide legend

        //setup x-axis
        setupXAxis(lineChart.getXAxis());

        //setup y-axis
        setupYAxis(lineChart.getAxisLeft());
        setupYAxis(lineChart.getAxisRight());

        //setup interaction
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setHighlightPerTapEnabled(true);

        StudyDetailMarkerView markerView = new StudyDetailMarkerView(context, R.layout.study_detail_marker_view);
        lineChart.setMarkerView(markerView);

        lineChart.setSelected(true);
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                String daySelected = lineXString.get(e.getXIndex());
                highlightAt(e.getXIndex(), dataSetIndex);
                studyDetailPresenter.getRank(daySelected);
                studyDetailPresenter.getAppUsage(daySelected);
            }

            @Override
            public void onNothingSelected() {
                remainHighlight();
            }
        });

        lineChart.setEnabled(false);        //hide self before data
    }

    private void setupXAxis(XAxis xAxis) {
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLimitLinesBehindData(true);

        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(GRID_COLOR);
        xAxis.setGridLineWidth(GRID_WIDTH);

        xAxis.setDrawAxisLine(false);
        xAxis.setAxisLineColor(GRID_COLOR);
        xAxis.setAxisLineWidth(GRID_WIDTH);
        xAxis.setAxisMinValue(-0.5f);
        xAxis.setAxisMaxValue(6.5f);
        xAxis.setAvoidFirstLastClipping(false);
    }

    private void setupYAxis(YAxis yAxis) {
        yAxis.setDrawLabels(false);
        yAxis.setDrawTopYLabelEntry(false);

        yAxis.setDrawGridLines(true);
        yAxis.setGridColor(GRID_COLOR);
        yAxis.setGridLineWidth(GRID_WIDTH);
    }

    private List<String> lineXString;

    private LineData getLineData(List<StudyTimeModel> studyTimeModels) {
        lineXString = new ArrayList<>();
        for (StudyTimeModel studyTimeModel : studyTimeModels) {
            lineXString.add(studyTimeModel.getDay());
        }

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < studyTimeModels.size(); i++) {
            yValues.add(new Entry(studyTimeModels.get(i).getTotalHour(), i));
        }

        LineDataSet lineDataSet = new LineDataSet(yValues, "this is study time");
        lineDataSet.setColor(LINE_COLOR);
        lineDataSet.setLineWidth(LINE_WIDTH);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(Color.YELLOW);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(false);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);

        LineData lineData = new LineData(lineXString, lineDataSets);
        lineData.setHighlightEnabled(true);
        lineData.setValueFormatter(new HourFormatter());
        return lineData;
    }

    private List<Integer> pieColors;

    private void setupPieChart() {

        pieChart.setDescription("");    //hide description
        pieChart.setDrawCenterText(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawSliceText(true);

        pieChart.setRotationEnabled(false);
        pieChart.setRotationAngle(0);

        pieChart.setUsePercentValues(true);

        pieChart.setEnabled(false);

        pieColors = getPieColors();
    }

    private List<Integer> getPieColors() {
        List<Integer> pieColors = new ArrayList<>();
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_0));
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_1));
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_2));
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_3));
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_4));
        pieColors.add(ContextCompat.getColor(context, R.color.pc_study_detail_5));
        return pieColors;
    }

    private PieData getPieData(List<AppUsageModel> appUsageModels) {

        List<String> xValues = new ArrayList<>();
        Collections.sort(appUsageModels);
        Collections.reverse(appUsageModels);
        for (AppUsageModel appUsageModel : appUsageModels) {
            xValues.add(appUsageModel.getAppName());
        }

        List<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < appUsageModels.size(); i++) {
            yValues.add(new Entry(appUsageModels.get(i).getHour(), i));
        }

        PieDataSet pieDataSet = new PieDataSet(yValues, "App time consume");
        pieDataSet.setSliceSpace(0f);
        if (pieColors == null) {
            pieColors = getPieColors();
        }
        pieDataSet.setColors(pieColors.subList(0, StudyTimeModel.BRIEF_SIZE));

        pieDataSet.setSelectionShift(0);
        pieDataSet.setDrawValues(false);        //hide percent values
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xValues, pieDataSet);
        return pieData;
    }

    private int xIndex;
    private int dataSetIndex;

    private void highlightAt(int xIndex, int dataSetIndex) {
        this.xIndex = xIndex;
        this.dataSetIndex = dataSetIndex;
        lineChart.highlightValue(xIndex, dataSetIndex);
    }

    private void remainHighlight() {
        lineChart.highlightValue(xIndex, dataSetIndex);
    }

    private void highlightLast() {
        ILineDataSet lineDataSet = lineChart.getLineData().getDataSets().get(0);
        xIndex = lineDataSet.getEntryCount() - 1;
        dataSetIndex = 0;
        remainHighlight();
    }
}
