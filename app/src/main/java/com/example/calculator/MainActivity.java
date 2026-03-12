package com.example.calculator;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity for the Calculator application.
 * This class handles the user interface and basic arithmetic operations (Add, Subtract, Multiply, Divide).
 */
public class MainActivity extends AppCompatActivity {

    // Operation constants to maintain consistency
    private static final String OP_ADD = "Add";
    private static final String OP_SUBTRACT = "Subtract";
    private static final String OP_MULTIPLY = "Multiply";
    private static final String OP_DIVIDE = "Divide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and setup click listeners for the calculator buttons
        initializeUI();
    }

    /**
     * Sets up the button click listeners by binding each button to the calculate method.
     */
    private void initializeUI() {
        findViewById(R.id.b_Add).setOnClickListener(v -> calculate(OP_ADD));
        findViewById(R.id.b_Subtract).setOnClickListener(v -> calculate(OP_SUBTRACT));
        findViewById(R.id.b_Multiply).setOnClickListener(v -> calculate(OP_MULTIPLY));
        findViewById(R.id.b_Divide).setOnClickListener(v -> calculate(OP_DIVIDE));
    }

    /**
     * Performs the arithmetic operation based on the user's input.
     * operation: The type of operation to perform (Add, Subtract, Multiply, Divide).
     */
    private void calculate(String operation) {
        // UI elements for input and displaying results
        EditText textN1 = findViewById(R.id.editTextNum1);
        EditText textN2 = findViewById(R.id.editTextNum2);
        EditText textAns = findViewById(R.id.editTextNumAns);

        try {
            // Get values from input fields and parse them as doubles
            double d1 = Double.parseDouble(textN1.getText().toString());
            double d2 = Double.parseDouble(textN2.getText().toString());
            double answer;

            // Execute the corresponding mathematical operation
            switch (operation) {
                case OP_ADD:
                    answer = d1 + d2;
                    break;
                case OP_SUBTRACT:
                    answer = d1 - d2;
                    break;
                case OP_MULTIPLY:
                    answer = d1 * d2;
                    break;
                case OP_DIVIDE:
                    // Guard against division by zero
                    if (d2 == 0) {
                        textAns.setText(R.string.error_div_by_0);
                        return;
                    }
                    answer = d1 / d2;
                    break;
                default:
                    answer = 0.0;
                    break;
            }

            // Update the answer field with the calculated result
            textAns.setText(String.valueOf(answer));

        } catch (NumberFormatException e) {
            // Display an error message if the user enters non-numeric text
            textAns.setText(R.string.invalid_input);
        }
    }
}
