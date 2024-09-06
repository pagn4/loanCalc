package com.example.a7;


import java.util.Arrays;



public class calculate {
    public float time;
    public float money;
    public float interest;

    public calculate(float time, float money, float interest) {
        this.time = time;
        this.money = money;
        this.interest = interest;
    }

    public double [] anuity() {
        int t = (int) time;
        double [] values = new double [t];

        double monthlyPayment;
        monthlyPayment=(interest/12*money)/(1-Math.pow((1+interest/12), -time));

        Arrays.fill(values, monthlyPayment);

        return values;
    }
    public double [] line(){
        int t = (int) time;
        double [] values = new double [t];

        double regularPay = money/time;
        for(int i=0;i<time;i++){
            values[i]=regularPay+money*(interest/12);
            values[i]=Math.round(values[i]*100.0)/100.0;
            money-=regularPay;
        }

        return values;

    }
}