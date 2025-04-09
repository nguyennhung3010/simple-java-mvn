package com.mycompany.app.util;

import com.mycompany.app.model.TenderDetail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {
    public static void writeToCsv(String filePath, List<TenderDetail> tenderList) {
        File file = new File(filePath);
        boolean fileExists = file.exists();
        // allowing append to end of file
        boolean append = true;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            // Only write header if we're NOT appending OR file doesn't exist yet
            if (!append || !fileExists) {
                writer.write(TenderDetail.getCsvHeader());
                writer.newLine();
            }

            for (TenderDetail tender : tenderList) {
                writer.write(tender.toCsvRow());
                writer.newLine();
            }

            System.out.println("✅ CSV " + (append ? "appended" : "written") + " successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("⚠️ Failed to write CSV: " + e.getMessage());
        }
    }

    public static void writeToCsvStr(String filePath, List<String> tenderList) {
        File file = new File(filePath);
        boolean fileExists = file.exists();
        // allowing append to end of file
        boolean append = true;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            // Only write header if we're NOT appending OR file doesn't exist yet
            if (!append || !fileExists) {
                writer.write(TenderDetail.getCsvHeader());
                writer.newLine();
            }

            for (String tender : tenderList) {
                writer.write(tender);
                writer.newLine();
            }

            System.out.println("✅ CSV " + (append ? "appended" : "written") + " successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("⚠️ Failed to write CSV: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        List arr = new ArrayList();
        String text = "=GetURL(C";
        for (int i = 1; i <= 250; i++) {
            var j = i*9;
            arr.add("'"+text + j + ")");
        }
        writeToCsvStr("src/main/resources/link.csv", arr);
    }
}
