package com.adnanalperavci.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ThirdActivity extends AppCompatActivity {
    TextView tvNum;
    String[] signs;
    TextView tvTitle3;
    SeekBar seekBar;
    Button btnCalc2;
    double result;
    double finalres;
    ValueAnimator colorAnim;
    String resMsg;
    String msg;
    double pow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_third);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvTitle3=findViewById(R.id.tvTitle3);
        colorAnim = ObjectAnimator.ofInt(tvTitle3, "textColor", Color.RED,Color.BLUE );
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        seekBar=findViewById(R.id.seekBar);
        tvTitle3=findViewById(R.id.tvTitle3);
        tvNum=findViewById(R.id.tvNum);
        btnCalc2=findViewById(R.id.btnCalc2);

        result=getIntent().getDoubleExtra("result",0);

        msg="Move the seekbar to select a power";
        Toast.makeText(ThirdActivity.this,msg,Toast.LENGTH_LONG).show();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tvNum.setText("Power: "+progress);
                pow=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnCalc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalres=Math.pow(result,pow);
                resMsg="Calculated Result is: "+finalres;
                makeAndShowDialog(resMsg);
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