package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static double calculateBmi(int weight, double meter) {
        return weight / (meter * meter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutCompat mainLayout = findViewById(R.id.llMain);
        AppCompatEditText edtWeight = findViewById(R.id.edtWeight);
        AppCompatEditText edtHeightFeet = findViewById(R.id.edtHeightFeet);
        AppCompatEditText edtHeightInch = findViewById(R.id.edtHeightInch);
        AppCompatTextView BMIResult = findViewById(R.id.BMIResult);
        AppCompatButton calculateBMI = findViewById(R.id.calculateBMI);

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWeight.getText().toString().isEmpty() ||
                        edtHeightFeet.getText().toString().isEmpty() ||
                        edtHeightInch.getText().toString().isEmpty()) {
                    // Handle empty input values
                    return;
                }

                int weight = Integer.parseInt(edtWeight.getText().toString());
                int feet = Integer.parseInt(edtHeightFeet.getText().toString());
                int inch = Integer.parseInt(edtHeightInch.getText().toString());

                int totalInches = (feet * 12) + inch;
                double totalCM = totalInches * 2.54;
                double totalMeter = totalCM / 100.0;

                double BMI = calculateBmi(weight, totalMeter);

                if (BMI > 25) {
                    BMIResult.setText(getString(R.string.overweight_message));
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.red));
                } else if (BMI < 18.5) {
                    BMIResult.setText(getString(R.string.underweight_message));
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
                } else {
                    BMIResult.setText(getString(R.string.healthy_message));
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }
        });
    }
}
