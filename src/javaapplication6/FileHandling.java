package javaapplication6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileHandling {
    static void writeReport(String text) {
        try (FileWriter fw = new FileWriter("ReportCard.txt", true)) {
            fw.write(text + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    static String readReport() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("ReportCard.txt"))) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append(System.lineSeparator());
        } catch (IOException e) {
            sb.append("No report found.");
        }
        return sb.toString();
    }
}