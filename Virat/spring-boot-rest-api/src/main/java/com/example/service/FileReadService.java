package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@Slf4j
@Service
public class FileReadService {

    public void readCsvFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(",");  // Adjust delimiter if needed
                // Process each column as needed
                for (String column : columns) {
                    System.out.println(column.trim());  // Example processing
                }
            }
        } catch (IOException e) {
            log.error("Error reading CSV file", e);
        }
    }

    public void readPdfFile(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // Print the extracted text (or process it as needed)
            System.out.println(text);

        } catch (IOException e) {
            log.error("Error reading PDF file", e);
        }
    }

}
