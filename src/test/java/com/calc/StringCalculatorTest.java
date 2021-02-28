package com.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    @DisplayName("GetCalledCount when the method Add was never called")
    void testCalledCountWhenNoMethodIsCalled() {
        assertEquals(0, new StringCalculator().getCalledCount());
    }

    @Test
    @DisplayName("GetCalledCount when the method Add is called multiple times")
    void testCalledCountWhenMethodIsCalledMultipleTimes() {
        StringCalculator sc = new StringCalculator();
        int n = 5;
        for(int i=0; i<n; i++) {
            int dump = sc.add(String.valueOf(i));
        }
        assertEquals(n, sc.getCalledCount());
    }

    @Test
    @DisplayName("Test Empty Input")
    void testEmptyInput() {
        assertEquals(0, calc.add(""));
    }

    @Test
    @DisplayName("Test Add with One Input Method")
    void testOneInputNumber() {
        assertEquals(3, calc.add("3"));
    }

    @Test
    @DisplayName("Test Add with Two Input Numbers")
    void testTwoInputNumbers() {
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    @DisplayName("Test Add with Multiple Input Numbers")
    void testMultipleInputNumbers() {
        assertEquals(2+5+6+34+83+42, calc.add("2,5,6,34,83,42"));
    }

    @Test
    @DisplayName("Test Add with Two Input Numbers with a New Line as a separator")
    void testTwoInputNumbersWithNewLine() {
        assertEquals(7, calc.add("3\n4"));
    }

    @Test
    @DisplayName("Test Add with Two Input Numbers with a New Line and comma as separator")
    void testMultipleInputNumbersWithNewLine() {
        assertEquals(2+5+6+34+83+42, calc.add("2,5,6\n34,83\n42"));
    }

    @Test
    @DisplayName("Test using custom delimiters")
    void testCustomDelimiter() {
        assertEquals(9, calc.add("//-\n2-3-4"));
        assertEquals(9, calc.add("//;\n2;3;4"));
    }

    @Test
    @DisplayName("Test Exception when passing single negative Input")
    void testExceptionThrownOnNegativeInput() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("2,-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    @DisplayName("Test Exception when passing negative Input with custom delimiter")
    void testExceptionThrownOnNegativeInputWithDelimiter() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    @DisplayName("Test Exception when passing multiple negative Input")
    void testExceptionThrownOnMultipleNegativeInput() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n-2#-3"));
        assertEquals("Negatives not allowed [-2, -3]", exc.getMessage());
    }

    @Test
    @DisplayName("Test for ignoring input greater than 1000")
    void testNumbersGreaterThan1000() {
        assertEquals(0, calc.add("1001"));
        assertEquals(3, calc.add("1001,3"));
        assertEquals(62, calc.add("1001,3,59,5679"));
    }

    @Test
    @DisplayName("Test single custom delimiter with any length")
    void testOneCustomDelimiterWithAnyLength() {
        assertEquals(9, calc.add("//[---]\n2---3---4"));
        assertEquals(9, calc.add("//[;;]\n2;;3;;4"));
        assertEquals(6, calc.add("//[**]\n1**2**3"));
    }

    @Test
    @DisplayName("Test multiple custom delimiters with singular length")
    void testMultipleDelimiters() {
        assertEquals(9, calc.add("//[%][#]\n2%3#4"));
        assertEquals(9, calc.add("//[-][@]\n2@3-4"));
        assertEquals(15, calc.add("//[-][@][*]\n2@3-4*6"));
    }

    @Test
    @DisplayName("Test multiple custom delimiter with multiple length")
    void testMultipleDelimitersWithMultipleLength() {
        assertEquals(9, calc.add("//[%%][##]\n1##5%%3"));
        assertEquals(11, calc.add("//[%%][###][@@]\n1###5%%3@@2"));
    }

    @Test
    @DisplayName("Test exception when the line contains multiple new lines as separators with custom delimiter/s")
    void testExceptionThrownWhenMultipleLineInvalidInput() {
        Exception exc;
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4"));
        assertEquals("Invalid Input", exc.getMessage());
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4\n5\n6#7"));
        assertEquals("Invalid Input", exc.getMessage());
    }
}