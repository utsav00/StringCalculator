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
        String delimiters = ",|[\n|(\r\n)]"; //Delimiters - Either ',' or '\n' or '\r\n'

        if (numbers.startsWith("//")) {
            String[] input = numbers.split("\n");
            if (input.length > 2)
                throw new RuntimeException("Invalid Input");
            delimiters = input[0];
            nums = input[1];
        }
        return add(nums, delimiters);
    }

    private int add(String numbers, String delimiters) {
        int sum = 0;

        if (numbers.isBlank())
            return sum;

        String[] numbersSeparated = numbers.split(getDelimiter(delimiters));
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String num : numbersSeparated) {
            int n = Integer.parseInt(num);
            if (n < 0)
                negativeNumbers.add(n);
            else if (n <= 1000)
                sum += n;
        }

        if (negativeNumbers.size() > 0)
            throw new RuntimeException("Negatives not allowed " + negativeNumbers.toString());

        return sum;
    }

    private String getDelimiter(String delimiters) {
        if (delimiters.contains("*")) // Escape characters for meta character for regex}
            delimiters = delimiters.replace("*", "\\*");

        if (!delimiters.startsWith("//"))
            return delimiters;
        else
            delimiters = delimiters.replace("//", "");

        String[] delimitersList = delimiters.split("]");
        String delim = "";

        for (String d : delimitersList) {
            delim = delim + d.substring(d.indexOf("[") + 1) + "|";
        }
        return delim.substring(0, delim.length() - 1);
    }
}