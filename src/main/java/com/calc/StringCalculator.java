package com.calc;

public class StringCalculator {
    public int add(String numbers) {
        int sum = 0;
        if (numbers.isBlank())
            return sum;
        String[] numbersSeparated = numbers.split(",");
        if (numbersSeparated.length > 2)
            throw new RuntimeException("Invalid number of arguments");
        for (String num: numbersSeparated)
            sum += Integer.parseInt(num);
        return  sum;
    }
}
