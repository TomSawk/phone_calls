package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MyFileHandler {
    static String path = System.getProperty("user.dir") + "/src/main/java/org/example/file";
    public static void createTextFile(String filename) {
        try {
            FileWriter writer = new FileWriter(path + filename);
            writer.close();
            System.out.println("Text file created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the text file: " + e.getMessage());
        }
    }
    public static void fillFileWithData(String filename, int K, int R){
        int CallerID, CaleeID, CallTime;
        Random random = new Random();
        int mean = 226; //a mean time for a phone call
        int std = 36;

        try {
            FileWriter writer = new FileWriter(path + filename);

            for (int i = 0; i < R; i++) {
                CallerID = random.nextInt(K);
                do {
                    CaleeID = random.nextInt(K);
                } while (CaleeID == CallerID);
                CallTime = (int) (mean + random.nextGaussian() * std);

                writer.write(CallerID + " " + CaleeID + " " + CallTime);
                writer.write(System.lineSeparator());
            }

            writer.close();
            System.out.println("Text file filled successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while filling the text file: " + e.getMessage());
        }
    }
    public static void readDataFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path +filename));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split(" ");
                if (numbers.length == 3) {
                    int number1 = Integer.parseInt(numbers[0]);
                    int number2 = Integer.parseInt(numbers[1]);
                    int number3 = Integer.parseInt(numbers[2]);

                    new Call(number1, number2,number3);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the text file: " + e.getMessage());
        }
    }
}