package com.example.android.car_loan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
private EditText carcost;
private EditText down;
private EditText APR;
private SeekBar time;
private TextView total;
private TextView tlabel;
private RadioButton loan;
private RadioButton lease;
private double cost;
private double apr;
private double d;
private double monthlycost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carcost=findViewById(R.id.carinput);
        down=findViewById(R.id.downinput);
        APR=findViewById(R.id.APRinput);
        time=findViewById(R.id.months);
        total=findViewById(R.id.output);
        tlabel=findViewById(R.id.timelabel);
        loan=findViewById(R.id.loan);
        lease=findViewById(R.id.lease);
        monthlycost=0.0;
        carcost.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String s= carcost.getText().toString();
                        cost=Double.parseDouble(s);
                        return false;
                    }
                }
        );
        down.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String t= down.getText().toString();
                        d=Double.parseDouble(t);
                        return false;
                    }
                }
        );
        APR.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String w=APR.getText().toString();
                        apr=Double.parseDouble(w);
                        return false;
                    }
                }
        );
        time.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                       tlabel.setText(i+"Months");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );



    }
    public void Calculate(View v){
        if(loan.isChecked()) {
            apr=apr/100;
            apr=apr/12;
            double n = time.getProgress();
            cost=cost-d;
            monthlycost=apr*cost/(1-(Math.pow(1+apr,-n)));
            //total.setText("$"+String.format("%.2f",monthlycost));
        }else if(lease.isChecked()){
            cost=(cost/3)-d;
            apr=apr/100;
            apr=apr/12;
            double n=36;
            monthlycost=apr*cost/(1-(Math.pow(1+apr,-n)));
            total.setText("$"+String.format("%.2f",monthlycost));
        }
    }
}
