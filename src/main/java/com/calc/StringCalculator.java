package com.calc;

import java.nio.file.LinkPermission;

public class StringCalculator {
    public int add(String numbers) {
        String nums = numbers;
        String delimiter = ",|[\n|(\r\n)]"; //Delimiters - Either ',' or '\n' or '\r\n'
        if (numbers.startsWith("//")) {
            String[] input = numbers.split("\n", 2);
            if (input.length > 2)
                throw new RuntimeException("Invalid Input");
            delimiter = input[0].substring(2);
            nums = input[1];
        }
        return add(nums, delimiter);
    }

    public int add(String numbers, String delimiter) {
        int sum = 0;
        if (numbers.isBlank())
            return sum;
        String[] numbersSeparated = numbers.split(delimiter);
        for (String num: numbersSeparated) {
            int n = Integer.parseInt(num);
            if (n<0) throw new RuntimeException("Negatives not allowed");
            sum += Integer.parseInt(num);
        }
        return  sum;
    }
}
