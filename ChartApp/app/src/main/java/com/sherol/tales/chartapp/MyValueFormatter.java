package com.sherol.tales.chartapp;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by tales on 26/10/2015.
 */
public class MyValueFormatter implements ValueFormatter{

    private DecimalFormat mFormat;

    public MyValueFormatter(){
        mFormat = new DecimalFormat("###,###,##0");
    }
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler){
        return "R$ " + mFormat.format(value);
    }
}
