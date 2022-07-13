package com.example.monny;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class spendbycategory extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendbycategory);

        pieChart=findViewById(R.id.piechartw);
        loadPieChartData();

    }

    private void  setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);


    }

    private void loadPieChartData(){
        ArrayList<PieEntry>entries=new ArrayList<>();
        entries.add(new PieEntry(0.2f,"Personal"));
        entries.add(new PieEntry(0.15f,"Food"));
        entries.add(new PieEntry(0.10f,"Groceries"));
        entries.add(new PieEntry(0.21f,"Bills"));
        entries.add(new PieEntry(0.3f,"Transportation"));
        entries.add(new PieEntry(0.04f,"Other"));

        ArrayList<Integer>colors=new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(entries,"Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }


}