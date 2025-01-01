package com.example.test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PadStringWithStreams {
    public static void main(String[] args) {

        String paddedString = padWithLeadingZeros("1234567890");

        System.out.println(paddedString);
    }

    public static String padWithLeadingZeros(String input) {


        // Use streams to create a string with leading zeros
        String leadingZeros = IntStream.range(0, 15 - input.length())
                .mapToObj(i -> "0")
                .collect(Collectors.joining());

        // Concatenate leading zeros and the user input
        return leadingZeros + input;
    }
}