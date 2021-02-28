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
}