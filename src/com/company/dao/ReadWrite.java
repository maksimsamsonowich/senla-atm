package com.company.dao;

import com.company.entity.Card;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWrite {

    public static ArrayList<Card> loadBase(String path) throws IOException {

        String[] temp;
        ArrayList<Card> cardsDatabase = new ArrayList<>();

        FileReader fileReader = new FileReader(path);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNext()) {
            temp = scanner.nextLine().split(" ");

            if (Boolean.parseBoolean(temp[3])) {
                cardsDatabase.add(new Card(temp[0], Integer.parseInt(temp[1]), Double.parseDouble(temp[2]), Boolean.parseBoolean(temp[3]), String.valueOf(temp[4])));
            }
            else
                cardsDatabase.add(new Card(temp[0], Integer.parseInt(temp[1]), Double.parseDouble(temp[2]), Boolean.parseBoolean(temp[3])));
        }

        fileReader.close();
        scanner.close();

        return cardsDatabase;
    }

    public static void overwriteTheDatabase(String path, ArrayList<Card> cardsDatabase) throws IOException {
        FileWriter fileWriter = new FileWriter(path, false);

        cardsDatabase.forEach(card -> {
            try {
                fileWriter.write(card.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fileWriter.close();
    }

    public static double getAtmBalance(String path) throws IOException {
        double balance = 0;

        FileReader fileReader = new FileReader(path);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNext()) {
            balance = Double.parseDouble(scanner.nextLine());
        }

        fileReader.close();
        scanner.close();

        return balance;
    }

    public static void overwriteTheAtmBalance(String path, double balance) throws IOException {
        FileWriter fileWriter = new FileWriter(path, false);

        fileWriter.write(Double.toString(balance) + "\n");

        fileWriter.close();
    }

}
