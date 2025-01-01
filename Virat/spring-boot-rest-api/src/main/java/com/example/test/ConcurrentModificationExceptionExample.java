package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcurrentModificationExceptionExample {
    public static void main(String[] args) {
        concurrentModificationException();
        concurrentModificationException2();
        // concurrentModificationException3();
        // concurrentModificationException4();
    }

    private static void concurrentModificationException() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        list.removeIf(word -> word.equals("a"));
        System.out.println("print list after remove : " + list);
    }

    private static void concurrentModificationException2() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c", "d"));
        list.removeIf(word -> word.equals("a"));
        System.out.println("print list after remove : " + list);
    }

    private static void concurrentModificationException3() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.removeIf(word -> word.equals("a"));
        System.out.println("print list after remove : " + list);
    }

    private static void concurrentModificationException4() {
        List<String> list = List.of("a", "b", "c", "d");
        list.removeIf(word -> word.equals("a"));
        System.out.println("print list after remove : " + list);
    }
}
