package com.example.a7;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class graph extends AppCompatActivity {
    GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        double[] values = getIntent().getDoubleArrayExtra("values");

        graphView = findViewById(R.id.idGraphView);


        LineGraphSeries<DataPoint> series;
        DataPoint[] dataPoints = new DataPoint[values.length];

        for (int i = 0; i < values.length; i++) {
            dataPoints[i] = new DataPoint(i, values[i]);
        }

        series = new LineGraphSeries<>(dataPoints);
        graphView.setTitle("Graph");
        graphView.setTitleTextSize(18);
        graphView.addSeries(series);

    }
}