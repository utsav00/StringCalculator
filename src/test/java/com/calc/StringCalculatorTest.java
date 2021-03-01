package com.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringCalculatorTest {
    private StringCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new StringCalculator();
    }

    @Test
    void test_called_count_when_no_method_is_called() {
        assertEquals(0, new StringCalculator().getCalledCount());
    }

    @Test
    void called_count_when_method_is_called_multiple_times() {
        StringCalculator sc = new StringCalculator();
        int n = 5;
        for (int i = 0; i < n; i++) {
            int dump = sc.add(String.valueOf(i));
        }
        assertEquals(n, sc.getCalledCount());
    }

    @Test
    void empty_input() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void one_input_number() {
        assertEquals(3, calc.add("3"));
    }

    @Test
    void two_input_numbers() {
        assertEquals(3, calc.add("1,2"));
    }

    @Test
    void multiple_input_numbers() {
        assertEquals(2 + 5 + 6 + 34 + 83 + 42, calc.add("2,5,6,34,83,42"));
    }

    @Test
    void two_input_numbers_with_new_line() {
        assertEquals(7, calc.add("3\n4"));
    }

    @Test
    void multiple_input_numbers_with_new_line() {
        assertEquals(2 + 5 + 6 + 34 + 83 + 42, calc.add("2,5,6\n34,83\n42"));
    }

    @Test
    void custom_delimiter() {
        assertEquals(9, calc.add("//-\n2-3-4"));
        assertEquals(9, calc.add("//;\n2;3;4"));
    }

    @Test
    void exception_thrown_on_negative_input() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("2,-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    void exception_thrown_on_negative_input_with_delimiter() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#-3"));
        assertEquals("Negatives not allowed [-3]", exc.getMessage());
    }

    @Test
    void exception_thrown_on_multiple_negative_input() {
        Exception exc = assertThrows(Exception.class, () -> calc.add("//[#]\n-2#-3"));
        assertEquals("Negatives not allowed [-2, -3]", exc.getMessage());
    }

    @Test
    void numbers_greater_than_1000() {
        assertEquals(0, calc.add("1001"));
        assertEquals(3, calc.add("1001,3"));
        assertEquals(62, calc.add("1001,3,59,5679"));
    }

    @Test
    void one_custom_delimiter_with_any_length() {
        assertEquals(9, calc.add("//[---]\n2---3---4"));
        assertEquals(9, calc.add("//[;;]\n2;;3;;4"));
        assertEquals(6, calc.add("//[**]\n1**2**3"));
    }

    @Test
    void multiple_delimiters() {
        assertEquals(9, calc.add("//[%][#]\n2%3#4"));
        assertEquals(9, calc.add("//[-][@]\n2@3-4"));
        assertEquals(15, calc.add("//[-][@][*]\n2@3-4*6"));
    }

    @Test
    void multiple_delimiters_with_multiple_length() {
        assertEquals(9, calc.add("//[%%][##]\n1##5%%3"));
        assertEquals(11, calc.add("//[%%][###][@@]\n1###5%%3@@2"));
    }

    @Test
    void exception_thrown_when_multiple_line_invalid_input() {
        Exception exc;
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4"));
        assertEquals("Invalid Input", exc.getMessage());
        exc = assertThrows(Exception.class, () -> calc.add("//[#]\n2#3\n4\n5\n6#7"));
        assertEquals("Invalid Input", exc.getMessage());
    }
}