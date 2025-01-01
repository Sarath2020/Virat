package com.example.completable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> words = new ArrayList<>();
        words.add("Geeks");
        words.add("For");
        words.add("Geeks");
        System.out.println(words);

        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<List<String>> future = service.submit(() -> removeElementsFromList(words));
        List<String> result = future.get();
        System.out.println(result);

        System.out.println("Hello World");

    }

    public static List<String> removeElementsFromList(List<String> words) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        words.removeIf(word -> word.equals("For"));
        return words;
    }



}
