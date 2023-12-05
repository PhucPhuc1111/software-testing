import static org.junit.jupiter.api.Assertions.assertEquals;
import  org.junit.jupiter.params.ParameterizedTest;
import  org.junit.jupiter.params.provider.CsvFileSource;

import com.example.Calculator;



public class TestCalculator {

    

    @ParameterizedTest
    @CsvFileSource(resources = "/testCaculatorSum.csv")
public void testSum(double num1, double num2, double expected) {
Calculator demo1 = new Calculator();
double result = demo1.sum(num1,num2);
assertEquals(expected,result);
}

    @ParameterizedTest
    @CsvFileSource(resources = "/testCaculatorMinus.csv")
public void testMinus(double num1, double num2, double expected) {
Calculator demo2 = new Calculator();
double result = demo2.minus(num1,num2);
assertEquals(expected,result);
}

    @ParameterizedTest
    @CsvFileSource(resources = "/testCaculatorDivide.csv")
public void testDivide(double num1, double num2, double expected) {
Calculator demo3 = new Calculator();
double result = demo3.divide(num1,num2);
assertEquals(expected,result);
}

    @ParameterizedTest
    @CsvFileSource(resources = "/testCaculatorMutiply.csv")
public void testMutiply(double num1, double num2, double expected) {
Calculator demo4 = new Calculator();
double result = demo4.multiply(num1,num2);
assertEquals(expected,result);
}
}
