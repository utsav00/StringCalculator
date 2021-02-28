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
    void testOneInput() {
        assertEquals(3, calc.add("3"));
    }

    @Test
    void testTwoInputs() {
        assertEquals(3, calc.add("1,2"));
    }
}