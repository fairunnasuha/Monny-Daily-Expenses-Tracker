package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class testGraf extends AppCompatActivity {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testgrafactivity);
        barChart=findViewById(R.id.BarChart);
        SetData();

        barDataSet=new BarDataSet(barEntries,"");
        barData=new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);
    }

    private void SetData(){
        barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(2f,0));
        barEntries.add(new BarEntry(4f,1));
        barEntries.add(new BarEntry(7f,2));
        barEntries.add(new BarEntry(8f,3));
        barEntries.add(new BarEntry(1f,4));
        barEntries.add(new BarEntry(9f,5));
        barEntries.add(new BarEntry(6f,6));
    }
}
