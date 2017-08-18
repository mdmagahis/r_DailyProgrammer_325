package com.ObjOri;

import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        LineNumberReader numLines = new LineNumberReader(new FileReader("resources/input1.txt"));
        char[][] matrix = new char[numLines.getLineNumber()][numLines.getLineNumber()];

        BufferedReader inputFile = new BufferedReader(new FileReader("resources/input1.txt"));
        String s;

        int line = numLines.getLineNumber();
        System.out.println(line);

        try {
            while ((s = inputFile.readLine()) != null) {
                String[] tokens = s.split(" ");
                int i = 0;

                for (String c : tokens) {
                    // Read line char-by-char, ignoring spaces

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Original code to read each line char-by-char
         * Not so efficient
         */
//        try {
//            while ((s = inputFile.readLine()) != null) {
//
//                for (char c : s.toCharArray()) {
//                    // Read line char-by-char, ignoring spaces
//                    if (c != ' ') {
//                        System.out.print(c);
//
//                    }
//                }
//                // Debug -- Separate by line
//                System.out.println();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }
}
