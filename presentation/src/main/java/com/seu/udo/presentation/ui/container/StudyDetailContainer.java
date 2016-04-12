package com.seu.udo.presentation.ui.container;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.seu.udo.presentation.mvp.view.StudyDetailView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Jeremy Xu on 2016/4/11 10:17
 * E-mail: jeremy_xm@163.com
 */
public class StudyDetailContainer extends FrameLayout implements StudyDetailView {

    //TODO:use context instead.
    @Inject Activity activity;
    @Inject StudyDetailPresenter studyDetailPresenter;

    @Bind(R.id.tv_study_rank) TextView rankTextView;
    @Bind(R.id.lc_study_rank) LineChart lineChart;
    @Bind(R.id.pc_app_while_study) PieChart pieChart;

    public StudyDetailContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.study_detail_container, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
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
        initializeLineChart();
        initializePieChart();
    }

    @Override
    public void renderRank(int rank) {
        rankTextView.setText(String.valueOf(rank));
    }

    @Override
    public void renderStudyTimes(List<StudyTimeModel> studyTimeModels) {
        lineChart.setData(getLineData(studyTimeModels));
        lineChart.setEnabled(true);
        lineChart.animateX(2000);
    }

    @Override
    public void renderAppUsages(List<AppUsageModel> appUsageModels) {
        PieData pieData = getPieData(appUsageModels);

        pieChart.setData(pieData);
        pieChart.setEnabled(true);
        pieChart.animateXY(2000, 1500);
    }

    private void initializeLineChart() {
        lineChart.setDrawBorders(false);

        lineChart.setDescription("");
        //lineChart.setNoDataTextDescription("You need to provide data");

        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF);

        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);

        lineChart.setBackgroundColor(Color.GREEN);

        lineChart.setHighlightPerTapEnabled(true);

        lineChart.getLegend().setEnabled(false);

        lineChart.setEnabled(false);

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                String daySelected = lineX.get(e.getXIndex());
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
        lineDataSet.setHighLightColor(Color.WHITE);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet);

        LineData lineData = new LineData(lineX, lineDataSets);

        return lineData;
    }

    private List<Integer> pieColors;

    private void initializePieChart() {

        pieColors = getPieColors();

        pieChart.setDescription("");

        pieChart.setDrawCenterText(false);

        pieChart.setDrawHoleEnabled(false);

        pieChart.setRotationEnabled(false);

        pieChart.setRotationAngle(0);

        pieChart.setUsePercentValues(true);

        pieChart.getLegend().setEnabled(false);

        pieChart.setDrawSliceText(true);

        pieChart.setEnabled(false);
    }

    private List<Integer> getPieColors() {
        List<Integer> pieColors = new ArrayList<>();
        //TODO:fix getColor.
        pieColors.add(activity.getColor(R.color.pc_study_detail_0));
        pieColors.add(activity.getColor(R.color.pc_study_detail_1));
        pieColors.add(activity.getColor(R.color.pc_study_detail_2));
        pieColors.add(activity.getColor(R.color.pc_study_detail_3));
        pieColors.add(activity.getColor(R.color.pc_study_detail_4));
        pieColors.add(activity.getColor(R.color.pc_study_detail_5));
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

        ArrayList<Integer> colors = new ArrayList<>();
        for (int i = 0; i < appUsageModels.size(); i++) {
            colors.add(pieColors.get(i));
        }
        pieDataSet.setColors(colors);

//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        float px = 5 * (metrics.densityDpi / 160f);
//        pieDataSet.setSelectionShift(px);
        pieDataSet.setDrawValues(false);
        pieDataSet.setValueTextSize(12f);

        PieData pieData = new PieData(xValues, pieDataSet);
        return pieData;
    }

    @OnClick(R.id.test_btn)
    protected void showChart() {
        studyDetailPresenter.getRank("周二");
        studyDetailPresenter.getStudyTimes();
        studyDetailPresenter.getAppUsage("周一");
    }
}
