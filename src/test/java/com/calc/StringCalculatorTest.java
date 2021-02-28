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
}