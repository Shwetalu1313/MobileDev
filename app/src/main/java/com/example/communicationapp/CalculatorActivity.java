package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class CalculatorActivity extends AppCompatActivity {

    TextView txtCalculateResult;
    Button btnone, btntwo, btnthree, btnfour, btnfive, btnsix, btnseven, btneight, btnnine, btnzero; //numbers

    Button btnadd, btnsub, btnmulti, btndiv; //operators

    Button btnclear, btnresult; //change result

    int numberone, numbertwo; //storage

    int operators, result;

    int switcher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        txtCalculateResult = findViewById(R.id.txtCalculateResult);

        btnclear = findViewById(R.id.btnclear);
        btnresult = findViewById(R.id.btnequal);

        btnadd = findViewById(R.id.btnadd);
        btnsub= findViewById(R.id.btnsub);
        btnmulti = findViewById(R.id.btnmulti);
        btndiv = findViewById(R.id.btndiv);

        btnzero = findViewById(R.id.btnzero);
        btnone = findViewById(R.id.btnone);
        btntwo = findViewById(R.id.btntwo);
        btnthree = findViewById(R.id.btnthree);
        btnfour = findViewById(R.id.btnfour);
        btnfive = findViewById(R.id.btnfive);
        btnsix = findViewById(R.id.btnsix);
        btnseven = findViewById(R.id.btnseven);
        btneight = findViewById(R.id.btneight);
        btnnine = findViewById(R.id.btnnine);


        //show result
        btnclear.setOnClickListener(view -> {
            txtCalculateResult.setText("0");
            switcher = 0;
        });

        btnresult.setOnClickListener(view -> {
            numbertwo = Integer.parseInt(txtCalculateResult.getText().toString());

            switch (operators) {
                case 1:
                    result = numberone + numbertwo;
                    txtCalculateResult.setText(Integer.toString(result));
                    break;
                case 2:
                    result = numberone - numbertwo;
                    txtCalculateResult.setText(Integer.toString(result));
                    break;
                case 3:
                    result = numberone * numbertwo;
                    txtCalculateResult.setText(Integer.toString(result));
                    break;
                case 4:
                    if (numbertwo == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_LONG).show();
                    } else {

                        if (numberone % numbertwo == 0){
                            result = numberone / numbertwo;
                        }
                        else {
                            double resulttwo = (double) numberone / numbertwo;
                            txtCalculateResult.setText(Double.toString(resulttwo));
                        }
                    }
                    break;
            }


            switcher = 1;
        });

        //end show result

        //operators
        btnadd.setOnClickListener(view -> {
            numberone = Integer.parseInt(txtCalculateResult.getText().toString());
            operators = 1;
            switcher = 0;
        });

        btnsub.setOnClickListener(view -> {
            if (switcher == 0) {
                txtCalculateResult.setText("0");
                switcher = 1;
            } else {
                numberone = Integer.parseInt(txtCalculateResult.getText().toString());
                operators = 2; // Set the operator to subtraction
                txtCalculateResult.setText(""); // Clear the display for the next number
            }
        });


        btnmulti.setOnClickListener(view -> {
            numberone = Integer.parseInt(txtCalculateResult.getText().toString());
            operators = 3;
            switcher = 0;
        });

        btndiv.setOnClickListener(view -> {
            numberone = Integer.parseInt(txtCalculateResult.getText().toString());
            operators = 4;
            switcher = 0;
        });
        //end operators

        //numbers


        // In the onCreate method, set click listeners for number buttons
        btnzero.setOnClickListener(view -> handleNumberButtonClick("0"));
        btnone.setOnClickListener(view -> handleNumberButtonClick("1"));
        btntwo.setOnClickListener(view -> handleNumberButtonClick("2"));
        btnthree.setOnClickListener(view -> handleNumberButtonClick("3"));
        btnfour.setOnClickListener(view -> handleNumberButtonClick("4"));
        btnfive.setOnClickListener(view -> handleNumberButtonClick("5"));
        btnsix.setOnClickListener(view -> handleNumberButtonClick("6"));
        btnseven.setOnClickListener(view -> handleNumberButtonClick("7"));
        btneight.setOnClickListener(view -> handleNumberButtonClick("8"));
        btnnine.setOnClickListener(view -> handleNumberButtonClick("9"));

        //end numbers
    }

    // Define a common method for number button clicks
    private void handleNumberButtonClick(String number) {
        if (switcher == 0) {
            txtCalculateResult.setText(number);
            switcher = 1;
        } else {
            txtCalculateResult.append(number);
        }
    }

}
