package com.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    void testCalledCountWhenNoMethodIsCalled() {
        assertEquals(0, new StringCalculator().getCalledCount());
    }

    @Test
    void testCalledCountWhenMethodIsCalledMultipleTimes() {
        StringCalculator sc = new StringCalculator();
        int n = 5;
        for(int i=0; i<n; i++) {
            int dump = sc.add(String.valueOf(i));
        }
        assertEquals(n, sc.getCalledCount());
    }

    @Test
    void testEmptyInput() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void testOneInputNumber() {
        assertEquals(3, calc.add("3"));
    }

    @Test
    void testTwoInputNumbers() {
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    void testMultipleInputNumbers() {
        assertEquals(2+5+6+34+83+42, calc.add("2,5,6,34,83,42"));
    }

    @Test
    void testTwoInputNumbersWithNewLine() {
        assertEquals(7, calc.add("3\n4"));
    }

    @Test
    void testMultipleInputNumbersWithNewLine() {
        assertEquals(2+5+6+34+83+42, calc.add("2,5,6\n34,83\n42"));
    }

    @Test
    void testCustomDelimiter() {
        assertEquals(9, calc.add("//-\n2-3-4"));
        assertEquals(9, calc.add("//;\n2;3;4"));
    }

    @Test
    void testExceptionThrownOnNegativeInput() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("2,-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    void testExceptionThrownOnNegativeInputWithDelimiter() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    void testExceptionThrownOnMultipleNegativeInput() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n-2#-3"));
        assertEquals("Negatives not allowed [-2, -3]", exc.getMessage());
    }

    @Test
    void testNumbersGreaterThan1000() {
        assertEquals(0, calc.add("1001"));
        assertEquals(3, calc.add("1001,3"));
        assertEquals(62, calc.add("1001,3,59,5679"));
    }

    @Test
    void testOneCustomDelimiterWithAnyLength() {
        assertEquals(9, calc.add("//[---]\n2---3---4"));
        assertEquals(9, calc.add("//[;;]\n2;;3;;4"));
        assertEquals(6, calc.add("//[**]\n1**2**3"));
    }

    @Test
    void testMultipleDelimiters() {
        assertEquals(9, calc.add("//[%][#]\n2%3#4"));
        assertEquals(9, calc.add("//[-][@]\n2@3-4"));
        assertEquals(15, calc.add("//[-][@][*]\n2@3-4*6"));
    }

    @Test
    void testMultipleDelimitersWithMultipleLength() {
        assertEquals(9, calc.add("//[%%][##]\n1##5%%3"));
        assertEquals(11, calc.add("//[%%][###][@@]\n1###5%%3@@2"));
    }

    @Test
    void testExceptionThrownWhenMultipleLineInvalidInput() {
        Exception exc;
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4"));
        assertEquals("Invalid Input", exc.getMessage());
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4\n5\n6#7"));
        assertEquals("Invalid Input", exc.getMessage());
    }
}