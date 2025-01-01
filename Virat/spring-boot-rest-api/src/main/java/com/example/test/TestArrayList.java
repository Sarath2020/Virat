package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestArrayList {


    public static void main(String[] args) {


        List<String> words = Stream.of("Geeks", "For", "Geeks").collect(Collectors.toList());
        words.removeIf(word -> word.equals("For"));
        System.out.println(words);


    }
}
