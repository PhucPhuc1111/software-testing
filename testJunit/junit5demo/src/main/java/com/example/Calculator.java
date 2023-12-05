package com.example;

public class Calculator {
    double result;
    public double sum(double num1, double num2) {
        
        return result = num1 + num2;
    }

    public double minus(double num1, double num2) {
        return result = num1 - num2;
    }

    public double divide(double num1, double num2) {
        if (num2 != 0) {
            return result = num1 / num2;
        }
        return num2;

    }

    public double multiply(double num1, double num2) {
        return result = num1 * num2;
    }

}
