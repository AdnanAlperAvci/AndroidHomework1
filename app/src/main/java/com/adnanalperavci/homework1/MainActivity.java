package com.adnanalperavci.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText editNum1;
    EditText editNum2;
    Button btnOpe;
    TextView tvTitle;
    ValueAnimator colorAnim;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvTitle=findViewById(R.id.tvTitle);
        colorAnim = ObjectAnimator.ofInt(tvTitle, "textColor", Color.RED,Color.BLUE );
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        btnOpe = findViewById(R.id.btnOpe);
        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);
        msg="Enter two values";
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

        btnOpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                intent = new Intent(MainActivity.this, SecondActivity.class);
                double num1 = Double.parseDouble(editNum1.getText().toString());
                double num2 = Double.parseDouble(editNum2.getText().toString());


                Bundle b = new Bundle();
                b.putDouble("num1", num1);
                b.putDouble("num2", num2);

                intent.putExtras(b);

                startActivity(intent);
            }
        });

    }

}