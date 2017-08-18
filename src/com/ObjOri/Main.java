package com.ObjOri;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader inputFile = new BufferedReader(new FileReader("resources/input1.txt"));
        String s;

        try {
            while ((s = inputFile.readLine()) != null) {
                for (char c : s.toCharArray()) {
                    // Read line to char
                    System.out.print(c);
                }
                // Debug -- Separate by line
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        char[][] matrix = new char[][];

    }
}
