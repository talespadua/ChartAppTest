package com.sherol.tales.chartapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.components.LimitLine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private Typeface mTf;
    private String[] mMonths = {"01 à 07", "08 à 14", "15 à 21", "22 à 28", "ATUAL"};

    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8084/get_balance";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Data d = gson.fromJson(response, Data.class);
                    createChart(d);
                    float[] vals = {d.val1, d.val2, d.val3, d.val4, d.val5};
                    float media = getAverage(vals);
                    TextView media_text = (TextView) findViewById(R.id.textView3);
                    media_text.setText(String.valueOf(media));
                    Log.d("Debug",response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Debug", "FALHOU");
                }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void createChart(Data d){
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        float[] vals = {d.val1, d.val2, d.val3, d.val4, d.val5};
        BarChart chart = (BarChart) findViewById(R.id.chart);
        //chart.setBackgroundColor(R.color.background);
        chart.setDescription("");
        chart.setMaxVisibleValueCount(10);
        chart.setPinchZoom(false);

        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setTextSize(13f);
        xAxis.setTextColor(R.color.grayLine);
        xAxis.setSpaceBetweenLabels(2);
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
        set1.setBarSpacePercent(0f);
        set1.setColors(new int[]{R.color.darkBlue, R.color.lightBlue, R.color.darkBlue, R.color.lightBlue, R.color.lastCol}, this);
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(13f);
        data.setValueTextColor(R.color.grayLine);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTypeface(mTf);

        LimitLine lim = new LimitLine(getAverage(vals)/*, "MÉDIA DAS ÚLTIMAS 4 SEMANAS"*/);
        lim.setLineColor(R.color.grayLine);
        lim.setLineWidth(1.4f);
        lim.enableDashedLine(18f, 7f, 0);
        lim.setTextSize(13f);
        lim.setTextColor(R.color.grayLine);
        leftYAxis.addLimitLine(lim);

        chart.setData(data);
        chart.animateY(2500);
        chart.getLegend().setEnabled(false);
        chart.invalidate();
    }

    private float getAverage(float[] array){
        float soma = 0;
        for(int i = 0; i < array.length; i++){
            soma += array[i];
        }

        return soma/array.length;
    }
}
