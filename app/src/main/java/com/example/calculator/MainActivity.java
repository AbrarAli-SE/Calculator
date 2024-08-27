package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0,
            bac, bc, bplus, bminus, bdiv, bmultiply, bmod, bequal, bdot, bsqrt, bfact, bsquare, bpi, bbrac1, bbrac2,
            bsin, bcos, btan, blog, bln;
    TextView tvmain, tvsec;

    String pi = "3.14159265";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and text views
        // Assign the correct IDs based on the XML file

        b1 = findViewById(R.id.buttonOne);
        b2 = findViewById(R.id.buttonTwo);
        b3 = findViewById(R.id.buttonThree);
        b4 = findViewById(R.id.buttonFour);
        b5 = findViewById(R.id.buttonFive);
        b6 = findViewById(R.id.buttonSix);
        b7 = findViewById(R.id.buttonSeven);
        b8 = findViewById(R.id.buttonEight);
        b9 = findViewById(R.id.buttonNine);
        b0 = findViewById(R.id.buttonZero);
        bac = findViewById(R.id.buttonAllClear);
        bc = findViewById(R.id.buttonBackSpace);
        bplus = findViewById(R.id.buttonAdd);
        bminus = findViewById(R.id.buttonSubtract);
        bdiv = findViewById(R.id.buttonDivision);
        bmod = findViewById(R.id.buttonPercentage); // For the modulus button
        bequal = findViewById(R.id.buttonEqual);
        bdot = findViewById(R.id.buttonDecimalPoint);
        bsqrt = findViewById(R.id.buttonSQRT);
        bfact = findViewById(R.id.buttonFactorial);
        bsquare = findViewById(R.id.buttonSquare);
        bpi = findViewById(R.id.buttonPi);
        bbrac1 = findViewById(R.id.buttonOpenBracket);
        bbrac2 = findViewById(R.id.buttonCloseBracket);
        bmultiply=findViewById(R.id.buttonMultiply);
        bsin = findViewById(R.id.buttonSin);
        bcos = findViewById(R.id.buttonCos);
        btan = findViewById(R.id.buttonTan);
        blog = findViewById(R.id.buttonLog);
        bln = findViewById(R.id.buttonLn);

        tvmain = findViewById(R.id.textInput);
        tvsec = findViewById(R.id.textEquation);


        // Button listeners for digits
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("9");
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("0");
            }
        });


        // Dot button handling
        bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                if (!val.contains(".")) {
                    appendToMain(".");
                }
            }
        });

        // Operation buttons with validation
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndAppendOperation("+");
            }
        });

        bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndAppendOperation("-");
            }
        });

        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndAppendOperation("/");
            }
        });

        bmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndAppendOperation("%"); // Modulus operator
            }
        });

        // Equals button (Evaluation)
        bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expression = tvmain.getText().toString();
                try {
                    if (isValidExpression(expression)) {
                        double result = eval(expression);
                        // Format the result
                        if (result % 1 == 0) { // Check if result is an integer
                            tvmain.setText(String.valueOf((int) result)); // Convert to integer
                        } else {
                            tvmain.setText(String.valueOf(result)); // Display as floating-point
                        }
                        tvsec.setText(expression);
                    } else {
                        tvmain.setText("Error");
                    }
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        // Clear buttons
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvmain.setText("");
                tvsec.setText("");
            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                if (!val.equals("")) {
                    val = val.substring(0, val.length() - 1);
                    tvmain.setText(val);
                }
            }
        });

        // Square root function
        // Square root function
        // Square root function

        bsqrt.setOnClickListener(v -> {
            String val = tvmain.getText().toString();
            try {
                if (!val.isEmpty()) {
                    double number = Double.parseDouble(val);
                    if (number >= 0) {
                        // For positive numbers, use the standard sqrt function
                        double result = Math.sqrt(number);
                        tvmain.setText(String.valueOf(result));
                        tvsec.setText("√" + val);
                    } else {
                        // For negative numbers, simplify the complex square root
                        String simplifiedResult = simplifySqrtNegative((int) number);
                        double numericalResult = calculateNumericalSqrt(number);
                        tvmain.setText(simplifiedResult + " = " + String.format("%.2f", numericalResult) + "i");
                        tvsec.setText("√(" + val + ")");
                    }
                } else {
                    tvmain.setText("Error");
                }
            } catch (Exception e) {
                tvmain.setText("Error");
            }
        });

        bsin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                try {
                    double radians = Math.toRadians(Double.parseDouble(val));
                    double result = Math.sin(radians);
                    tvmain.setText(String.valueOf(result));
                    tvsec.setText("sin(" + val + ")");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        bcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                try {
                    double radians = Math.toRadians(Double.parseDouble(val));
                    double result = Math.cos(radians);
                    tvmain.setText(String.valueOf(result));
                    tvsec.setText("cos(" + val + ")");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        btan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                try {
                    double radians = Math.toRadians(Double.parseDouble(val));
                    double result = Math.tan(radians);
                    tvmain.setText(String.valueOf(result));
                    tvsec.setText("tan(" + val + ")");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                try {
                    double result = Math.log10(Double.parseDouble(val));
                    tvmain.setText(String.valueOf(result));
                    tvsec.setText("log(" + val + ")");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        bln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvmain.getText().toString();
                try {
                    double result = Math.log(Double.parseDouble(val));
                    tvmain.setText(String.valueOf(result));
                    tvsec.setText("ln(" + val + ")");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


//         Operation buttons with validation
        bmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndAppendOperation("*");
            }
        });


        // Pi button
        bpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain(pi);
            }
        });

        // Square function
        bsquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double d = Double.parseDouble(tvmain.getText().toString());
                    double square = d * d;
                    tvmain.setText(String.valueOf(square));
                    tvsec.setText(d + "²");
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        // Factorial function
        bfact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int val = Integer.parseInt(tvmain.getText().toString());
                    if (val >= 0) {
                        int fact = factorial(val);
                        tvmain.setText(String.valueOf(fact));
                        tvsec.setText(val + "!");
                    } else {
                        tvmain.setText("Error");
                    }
                } catch (Exception e) {
                    tvmain.setText("Error");
                }
            }
        });

        // Brackets
        bbrac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain("(");
            }
        });

        bbrac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToMain(")");
            }
        });
    }

    // Append value to the main text view
    private void appendToMain(String str) {
        tvmain.append(str);
    }

    // Validate and append operation
    private void validateAndAppendOperation(String operation) {
        String val = tvmain.getText().toString();

        if (val.isEmpty()) {
            // Allow initial negative sign and parentheses
            if (operation.equals("-") || operation.equals("(")) {
                tvmain.append(operation);
            } else {
                tvmain.setText("Error");
            }
            return;
        }

        char lastChar = val.charAt(val.length() - 1);

        // Handle cases where the last character is an operator
        if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '%' || lastChar == '.') {
            tvmain.setText("Error");
            return;
        }

        // Handle cases where the last character is an open bracket
        if (lastChar == '(') {
            tvmain.append(operation);
            return;
        }

        tvmain.append(operation);
    }

    // Check if expression is valid
    private boolean isValidExpression(String expression) {
        if (expression.isEmpty()) return false;

        // Check for invalid patterns
        if (expression.matches(".*[+\\-*/%]$")) return false; // Ends with operator
        if (expression.matches("^[+*/%].*"))
            return false; // Starts with operator (excluding negative sign)

        // Check for balanced parentheses
        int openBrackets = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') openBrackets++;
            if (c == ')') openBrackets--;
        }
        if (openBrackets != 0) return false;

        return true;
    }

    // Factorial function
    private int factorial(int n) {
        if (n < 0) return -1; // Invalid factorial
        if (n <= 1) return 1;
        else return n * factorial(n - 1);
    }

    private double eval(String expression) {
        // Initialize a Rhino context
        Context rhino = Context.enter();

        // Set optimization level to -1 to avoid classloader issues
        rhino.setOptimizationLevel(-1);

        try {
            Scriptable scope = rhino.initStandardObjects();

            // Evaluate the expression
            Object result = rhino.evaluateString(scope, expression, "JavaScript", 1, null);

            // Ensure the result is a number
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else {
                throw new IllegalArgumentException("Expression did not evaluate to a number");
            }
        } finally {
            // Exit the Rhino context
            Context.exit();
        }
    }

    // Method to simplify square roots of negative numbers

    // Method to simplify square roots of negative numbers
    private String simplifySqrtNegative(int num) {
        num = Math.abs(num); // Make it positive to work with
        int outsideSqrt = 1;

        // Find the largest perfect square factor of num
        for (int i = 2; i * i <= num; ++i) {
            while (num % (i * i) == 0) {
                num /= (i * i);
                outsideSqrt *= i;
            }
        }

        // Return the result in the simplified form
        if (num == 1) { // Perfect square case
            return outsideSqrt + "i";
        } else {
            return outsideSqrt + "√" + num + "i";
        }
    }

    // Method to calculate the numerical square root of a negative number
    private double calculateNumericalSqrt(double num) {
        return Math.sqrt(Math.abs(num)); // Compute the square root of the absolute value
    }
}
