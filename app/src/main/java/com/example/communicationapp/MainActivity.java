package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSimpleCal, btnImageslider, btnMultiTables;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpleCal = findViewById(R.id.simpleCal);
        btnImageslider = findViewById(R.id.btnImageSlide);
        btnMultiTables = findViewById(R.id.btnMultiTables);



        btnSimpleCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the CalculatorActivity
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent); // This line starts the new activity
            }
        });//end of calculator

        btnImageslider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageSlideActivity.class);
                startActivity(intent);
            }
        });//end of MultiTables

        btnMultiTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiTableActivity.class);
            }
        });
    }
}
