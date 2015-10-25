package com.sherol.tales.chartapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private Typeface mTf;
    private String[] mMonths = {"jan", "feb", "mar", "abr", "mai", "jun"/*, "jul", "ago", "set", "out", "nov", "dez"*/};
    private int[] vals =        {200,   300,   400,   500,   350,   120,   200,   300,   400,   500,   350,   120};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        BarChart chart = (BarChart) findViewById(R.id.chart);
        chart.setBackgroundColor(223);
        chart.setDescription("Chart");
        chart.setMaxVisibleValueCount(10);
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setDrawGridLines(false);

        YAxis leftYAxis = chart.getAxisLeft();
        YAxis rigthYAxis = chart.getAxisRight();
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setDrawAxisLine(false);
        leftYAxis.setDrawLabels(false);

        rigthYAxis.setDrawGridLines(false);
        rigthYAxis.setDrawAxisLine(false);
        rigthYAxis.setDrawLabels(false);

        //DATASET
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < mMonths.length; i++) {
            xVals.add(mMonths[i % 12]);
            yVals.add(new BarEntry(vals[i], i));
        }
        BarDataSet set1 = new BarDataSet(yVals, "DataSet");
        set1.setBarSpacePercent(35f);
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);
        chart.setData(data);
        chart.animateY(2500);
        chart.getLegend().setEnabled(false);
        chart.invalidate();
    }
}
