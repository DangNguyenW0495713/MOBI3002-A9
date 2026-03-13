/**
 * Author: Dang Nguyen
 * ID: w0495713
 */

package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity for the Calculator application.
 * This class handles the user interface and basic arithmetic operations (Add, Subtract, Multiply, Divide).
 */
public class MainActivity extends AppCompatActivity {

    // Operation constants to maintain consistency
    private static final String OPERATION_ADD = "Add";
    private static final String OPERATION_SUBTRACT = "Subtract";
    private static final String OPERATION_MULTIPLY = "Multiply";
    private static final String OPERATION_DIVIDE = "Divide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and setup click listeners for the calculator buttons
        initializeUi();
    }

    /**
     * Sets up the button click listeners by binding each button to the calculate method.
     */
    private void initializeUi() {
        Log.i("MainActivity", "initializeUI");
        findViewById(R.id.b_Add).setOnClickListener(v -> calculate(OPERATION_ADD));
        findViewById(R.id.b_Subtract).setOnClickListener(v -> calculate(OPERATION_SUBTRACT));
        findViewById(R.id.b_Multiply).setOnClickListener(v -> calculate(OPERATION_MULTIPLY));
        findViewById(R.id.b_Divide).setOnClickListener(v -> calculate(OPERATION_DIVIDE));
    }

    /**
     * Performs the arithmetic operation based on the user's input.
     * @param operation: The type of operation to perform (Add, Subtract, Multiply, Divide).
     */
    private void calculate(String operation) {
        // UI elements for input and displaying results
        EditText mTextN1 = findViewById(R.id.editTextNum1);
        EditText mTextN2 = findViewById(R.id.editTextNum2);
        EditText mTextAns = findViewById(R.id.editTextNumAns);

        try {
            // Get values from input fields and parse them as doubles
            double d1 = Double.parseDouble(mTextN1.getText().toString());
            double d2 = Double.parseDouble(mTextN2.getText().toString());
            double answer;

            Log.i("MainActivity", "calculate: d1 = " + d1 + ", d2 = " + d2 + ", operation = " + operation);

            // Execute the corresponding mathematical operation
            switch (operation) {
                case OPERATION_ADD:
                    answer = d1 + d2;
                    break;
                case OPERATION_SUBTRACT:
                    answer = d1 - d2;
                    break;
                case OPERATION_MULTIPLY:
                    answer = d1 * d2;
                    break;
                case OPERATION_DIVIDE:
                    // Guard against division by zero
                    if (d2 == 0) {
                        mTextAns.setText(R.string.error_div_by_0);
                        return;
                    }
                    answer = d1 / d2;
                    break;
                default:
                    answer = 0.0;
                    break;
            }
            Log.i("MainActivity", "calculate: answer = " + answer);
            // Update the answer field with the calculated result
            mTextAns.setText(String.valueOf(answer));

        } catch (NumberFormatException e) {
            // Display an error message if the user enters non-numeric text
            mTextAns.setText(R.string.invalid_input);
            Log.e("MainActivity", "calculate: invalid input", e);
        }
    }
}
