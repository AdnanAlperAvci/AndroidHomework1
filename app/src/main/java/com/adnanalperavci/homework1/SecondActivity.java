package com.adnanalperavci.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class SecondActivity extends AppCompatActivity {
    Spinner spinner;
    String[] signs;
    Button btnCalc;
    Button btnPow;
    ImageView imageView;
    TextView tvTitle2;
    ValueAnimator colorAnim;
    String msg;
    String msg2;
    String resMsg;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnCalc=findViewById(R.id.btnCalc);
        btnPow=findViewById(R.id.btnPow);
        imageView=findViewById(R.id.imageView);
        tvTitle2=findViewById(R.id.tvTitle2);
        colorAnim = ObjectAnimator.ofInt(tvTitle2, "textColor", Color.RED,Color.BLUE );
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        Resources res=getResources();
        signs=res.getStringArray(R.array.signs);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter a=new ArrayAdapter(this, android.R.layout.simple_spinner_item,signs);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);

        Bundle b = getIntent().getExtras();
        double num1 = b.getDouble("num1");
        double num2 = b.getDouble("num2");
        msg="Choose Operation and Click Calculate Button to See the Result";
        Toast.makeText(SecondActivity.this,msg,Toast.LENGTH_LONG).show();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position=spinner.getSelectedItemPosition();

                if(position==0) {
                    imageView.setImageResource(R.drawable.addition);
                    result = num1 + num2;
                }
                else if(position==1) {
                    imageView.setImageResource(R.drawable.subtraction);
                    result = num1 - num2;
                }
                else if(position==2) {
                    imageView.setImageResource(R.drawable.multiplication);
                    result = num1 * num2;
                }
                else if(position==3) {
                    imageView.setImageResource(R.drawable.division);
                    result = num1 / num2;
                }

                DecimalFormat resFormat=new DecimalFormat("#.00");
                result= Double.parseDouble(resFormat.format(result));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resMsg="Calculated Result is: "+result;
                makeAndShowDialog(resMsg);
                msg2="Click Button to Get Power of Result";
                Toast.makeText(SecondActivity.this,msg2,Toast.LENGTH_LONG).show();
            }
        });
        btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=null;
                intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);

            }
        });
    }
    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("RESULT");
        box.setMessage(message);

        box.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        box.create();
        box.show();
    }
}