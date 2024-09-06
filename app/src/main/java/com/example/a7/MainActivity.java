package com.example.a7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText money;
    private EditText time;
    private Spinner graph;
    private EditText interest;
    private CheckBox month;
    private CheckBox year;
    double [] values;
    private EditText from;
    private EditText to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] graphs = {"Annuity", "Linear"};

        graph = findViewById(R.id.graph);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, graphs);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graph.setAdapter(ad);

        Button button = findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month = findViewById(R.id.month);
                year = findViewById(R.id.year);

                boolean is1 = month.isChecked();
                boolean is2 = year.isChecked();

                if ((is1 && is2) || (!is1 && !is2)) {
                    return;
                }

                time = findViewById(R.id.time);
                float length = Float.parseFloat(time.getText().toString());
                if (is2) {
                    length *= 12;
                }

                money = findViewById(R.id.money);
                float loan = Float.parseFloat(money.getText().toString());

                interest = findViewById(R.id.interest);
                float percent = Float.parseFloat(interest.getText().toString());
                percent = percent / 100;

                String selected = (String) graph.getSelectedItem();

                calculate cal = new calculate(length, loan, percent);

                if (selected.equals("Annuity")) {
                    values = cal.anuity();
                } else if (selected.equals("Linear")) {
                    values = cal.line();
                }

                from = findViewById(R.id.from);
                to = findViewById(R.id.to);
                String a = from.getText().toString();
                String b = to.getText().toString();

                int f;
                int t;


                if(!a.equals("")||!b.equals("")){
                    f=Integer.parseInt(a);
                    t= Integer.parseInt(b);
                }
                else{
                    t=0;
                    f=0;
                }

                double [] filValues = new double[t-f];
                float filLoan=loan;
                if(t!=0){
                    for(int i = 0;i<values.length; i++){
                        if(i<f){
                            filLoan-=values[i];
                        }
                        if(i>=f&&i<t){
                            filValues[i-f]=values[i];
                        }
                    }
                }

                Intent intent = new Intent(MainActivity.this, table.class);

                if(f!=0){
                    intent.putExtra("values", filValues);
                    intent.putExtra("total", filLoan);
                }
                else{
                    intent.putExtra("values", values);
                    intent.putExtra("total", loan);
                }

                intent.putExtra("interest", percent);
                startActivity(intent);

            }
        });
    }
}
