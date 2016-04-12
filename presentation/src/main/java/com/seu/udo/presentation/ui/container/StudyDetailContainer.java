package com.seu.udo.presentation.ui.container;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.seu.udo.R;
import com.seu.udo.presentation.internal.di.component.StudyComponent;
import com.seu.udo.presentation.mvp.model.AppUsageModel;
import com.seu.udo.presentation.mvp.model.StudyTimeModel;
import com.seu.udo.presentation.mvp.presenter.StudyDetailPresenter;
import com.seu.udo.presentation.mvp.view.StudyDetailView;
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
public class StudyDetailContainer extends FrameLayout implements StudyDetailView {

    private static final int LINE_CHART_ANITIME = 2000;
    private static final int PIE_CHART_ANITIME_X = 1500;
    private static final int PIE_CHART_ANITIME_Y = 1500;

    //TODO:use context instead.
    @Inject
    Activity activity;
    @Inject
    StudyDetailPresenter studyDetailPresenter;
    private Context context;

    @Bind(R.id.tv_study_rank)
    TextView rankTextView;
    @Bind(R.id.lc_study_rank)
    LineChart lineChart;
    @Bind(R.id.pc_app_while_study)
    PieChart pieChart;

    public StudyDetailContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.study_detail_container, this);

        this.context = context;
        ButterKnife.bind(this);
        initializeLineChart();
        initializePieChart();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //setVisibility(INVISIBLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
        studyDetailPresenter.detachView();
    }

    public void inject(StudyComponent studyComponent) {
        studyComponent.inject(this);
        studyDetailPresenter.attachView(this);
        studyDetailPresenter.getStudyTimes();
    }

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

    private void initializeLineChart() {
        lineChart.setDrawBorders(false);    //dont draw border
        lineChart.setDescription("");       //hide description
        lineChart.getLegend().setEnabled(false);    //hide legend
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.GREEN);

        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setHighlightPerTapEnabled(true);

        StudyDetailMarkerView markerView = new StudyDetailMarkerView(context, R.layout.study_detail_marker_view);
        lineChart.setMarkerView(markerView);

        //lineChart.setHighlighter(new ChartHighlighter());
        lineChart.setSelected(true);
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                String daySelected = lineX.get(e.getXIndex());
                lineChart.highlightValue(e.getXIndex(), dataSetIndex);
                studyDetailPresenter.getRank(daySelected);
                studyDetailPresenter.getAppUsage(daySelected);
            }

            @Override
            public void onNothingSelected() {
                String daySelected = lineX.get(lineX.size() - 1);
                studyDetailPresenter.getRank(daySelected);
                studyDetailPresenter.getAppUsage(daySelected);
            }
        });

        lineChart.setEnabled(false);        //hide self before data
    }

    private List<String> lineX;

    private LineData getLineData(List<StudyTimeModel> studyTimeModels) {
        lineX = new ArrayList<>();
        for (StudyTimeModel studyTimeModel : studyTimeModels) {
            lineX.add(studyTimeModel.getDay());
        }

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < studyTimeModels.size(); i++) {
            yValues.add(new Entry(studyTimeModels.get(i).getTotalHour(), i));
        }

        LineDataSet lineDataSet = new LineDataSet(yValues, "this is study time");
        lineDataSet.setLineWidth(1.75f);
        lineDataSet.setColor(Color.WHITE);
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

        LineData lineData = new LineData(lineX, lineDataSets);
        lineData.setHighlightEnabled(true);
        lineData.setValueFormatter(new HourFormatter());
        return lineData;
    }

    private List<Integer> pieColors;

    private void initializePieChart() {

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
        pieDataSet.setColors(pieColors.subList(0, StudyTimeModel.BRIEF_SIZE));

        pieDataSet.setSelectionShift(0);
        pieDataSet.setDrawValues(false);        //hide percent values
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xValues, pieDataSet);
        return pieData;
    }
}
