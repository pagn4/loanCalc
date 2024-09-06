package com.example.a7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class table extends AppCompatActivity {
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        this.setTitle("Table");
        double[] values = getIntent().getDoubleArrayExtra("values");
        float money = getIntent().getFloatExtra("total", 0.0f);
        float interest = getIntent().getFloatExtra("interest", 0.0f);
        table=findViewById(R.id.table);
        String tableDataForSaving="";
        double left=money;

        for (int i = 0;i<values.length;i++ ){

            TableRow row = new TableRow(this);
            TextView tv1=new TextView(this);
            TextView tv2=new TextView(this);
            TextView tv3=new TextView(this);

            tv1.setText(String.valueOf((double) Math.round((values[i]-left*(interest/12))*100)/100));
            tv2.setText(String.valueOf(((double)Math.round((left*(interest/12))*100)/100)));
            tv3.setText(String.valueOf((double)(Math.round(left*100))/100));
            tableDataForSaving+=String.valueOf((double) Math.round((values[i]-left*(interest/12))*100)/100)+"    "
                    +String.valueOf(((double)Math.round((left*(interest/12))*100)/100))+"   "
                    +String.valueOf((double)(Math.round(left*100))/100);

            int paddingPx = getResources().getDimensionPixelSize(R.dimen.textview_padding); // Get padding in pixels
            tv1.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
            tv2.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
            tv3.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);

            row.setBackground(getResources().getDrawable(R.drawable.border));


            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            table.addView(row);

            left-=((double)(Math.round((values[i]-left*(interest/12))*100))/100);

        }
        Button button = findViewById(R.id.graph);
        String finalTableDataForSaving = tableDataForSaving;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(table.this, graph.class);
                intent.putExtra("values", values);  // Assuming you want to pass values to the new activity
                startActivity(intent);
            }
        });

    }
}