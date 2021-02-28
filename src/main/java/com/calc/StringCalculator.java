package com.calc;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    private static int calledCount;

    public StringCalculator() {
        calledCount = 0;
    }

    public int getCalledCount() {
        return calledCount;
    }

    public int add(String numbers) {
        calledCount++;
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

    private int add(String numbers, String delimiter) {
        int sum = 0;
        if (numbers.isBlank())
            return sum;
        String[] numbersSeparated = numbers.split(delimiter);
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String num: numbersSeparated) {
            int n = Integer.parseInt(num);
            if (n<0)
                negativeNumbers.add(n);
            else if (n <= 1000)
                sum += n;
        }

        if (negativeNumbers.size() > 0)
            throw new RuntimeException("Negatives not allowed " + negativeNumbers.toString());

        return  sum;
    }
}
