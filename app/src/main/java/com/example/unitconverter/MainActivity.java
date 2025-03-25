package com.example.unitconverter;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge mode for the activity
        EdgeToEdge.enable(this);

        // Set the content view for the activity
        setContentView(R.layout.activity_main);

        //  Set up window insets listener to handle system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the convert button and set its click listener
        Button convertButton = findViewById(R.id.button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected options from the spinners
                Spinner sp1 = (Spinner) findViewById(R.id.spinner);
                String option1 = sp1.getSelectedItem().toString();
                Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
                String option2 = sp2.getSelectedItem().toString();

                // Get the input value from the edit text
                EditText editTextValue1 = findViewById(R.id.editText);
                TextView resultTextValue = findViewById(R.id.resultTextView);

                // Get the error text views
                TextView emptyErrorText = findViewById(R.id.emptyErrorTextView);
                TextView noUnitErrorText = findViewById(R.id.noUnitErrorTextView);

                // Check if the input value is valid and the unit is selected
                if ((editTextValue1.getText().toString().isEmpty() || editTextValue1.getText().toString().equals(".")) && ((option1.equals("Select a unit")) || (option2.equals("Select a unit"))))  {
                    emptyErrorText.setVisibility(View.VISIBLE);
                    noUnitErrorText.setVisibility(View.VISIBLE);
                } else if (editTextValue1.getText().toString().isEmpty() || editTextValue1.getText().toString().equals(".")) {
                    emptyErrorText.setVisibility(View.VISIBLE);
                    noUnitErrorText.setVisibility(View.INVISIBLE);
                    resultTextValue.setText("");
                } else if ((option1.equals("Select a unit")) || (option2.equals("Select a unit"))) {
                    noUnitErrorText.setVisibility(View.VISIBLE);
                    emptyErrorText.setVisibility(View.INVISIBLE);
                    resultTextValue.setText("");
                } else {
                    // Clear the error messages
                    noUnitErrorText.setVisibility(View.INVISIBLE);
                    emptyErrorText.setVisibility(View.INVISIBLE);

                    // Parse the EditText value to Double type
                    double value1 = Double.parseDouble(editTextValue1.getText().toString());
                    double value2 = 0;

                    // Perform the unit conversion based on the selected options
                    switch (option1) {
                        case "Meter":
                            switch (option2) {
                                case "Meter":
                                    value2 = value1;
                                    break;
                                case "Millimeter":
                                    value2 = value1 * 1000;
                                    break;
                                case "Mile":
                                    value2 = value1 * 0.000621371192;
                                    break;
                                case "Foot":
                                    value2 = value1 * 3.2808399;
                                    break;
                            }
                            break;
                        case "Millimeter":
                            switch (option2) {
                                case "Meter":
                                    value2 = value1 / 1000;
                                    break;
                                case "Millimeter":
                                    value2 = value1;
                                    break;
                                case "Mile":
                                    value2 = value1 / 1.6093E+6;
                                    break;
                                case "Foot":
                                    value2 = value1 / 304.8;
                                    break;
                            }
                            break;
                        case "Mile":
                            switch (option2) {
                                case "Meter":
                                    value2 = value1 * 1609.344;
                                    break;
                                case "Millimeter":
                                    value2 = value1 * 1609344;
                                    break;
                                case "Mile":
                                    value2 = value1;
                                    break;
                                case "Foot":
                                    value2 = value1 * 5280;
                                    break;
                            }
                            break;
                        case "Foot":
                            switch (option2) {
                                case "Meter":
                                    value2 = value1 / 3.2808399;
                                    break;
                                case "Millimeter":
                                    value2 = value1 * 304.8;
                                    break;
                                case "Mile":
                                    value2 = value1 / 5280;
                                    break;
                                case "Foot":
                                    value2 = value1;
                                    break;
                            }
                            break;
                    }
                    // Set the result text value
                    resultTextValue.setText(String.valueOf(value2));
                }
            }
        });
    }
}