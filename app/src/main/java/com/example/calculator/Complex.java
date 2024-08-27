package com.example.calculator;

public class Complex {
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        if (imaginary == 0) {
            return String.valueOf(real);
        } else if (real == 0) {
            return imaginary + "i";
        } else {
            return real + " + " + imaginary + "i";
        }
    }

    public static Complex sqrt(double number) {
        if (number < 0) {
            double magnitude = Math.sqrt(-number);
            return new Complex(0, magnitude);
        } else {
            return new Complex(Math.sqrt(number), 0);
        }
    }

    public static Complex simplifySqrtNegative(int num) {
        num = Math.abs(num);
        int outsideSqrt = 1;

        for (int i = 2; i * i <= num; ++i) {
            while (num % (i * i) == 0) {
                num /= (i * i);
                outsideSqrt *= i;
            }
        }

        if (num == 1) {
            return new Complex(0, outsideSqrt);
        } else {
            return new Complex(outsideSqrt, 0);
        }
    }
}

