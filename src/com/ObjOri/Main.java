package com.ObjOri;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Get size of maze, aka number of lines
        LineNumberReader numLines = new LineNumberReader(new FileReader("resources/input1.txt"));
        numLines.skip(Long.MAX_VALUE);

        // Create matrix with length and width of input maze
        String[][] matrix = new String[numLines.getLineNumber()][numLines.getLineNumber()];

        // Write contents of input to matrix
        BufferedReader inputFile = new BufferedReader(new FileReader("resources/input1.txt"));
        String s;

        int line = numLines.getLineNumber();
        System.out.println("Number of lines: " + line);

        try {
            // Skip first line (instruction line)
            inputFile.readLine();

            int rowNo = line - 1;

            while ((s = inputFile.readLine()) != null) {
                String[] tokens = s.split(" ");
                int colNo = 0;

                for (String c : tokens) {
                    // Write line char-by-char, ignoring spaces
                    matrix[rowNo][colNo] = c;
                    System.out.println(c + " written to matrix[" + rowNo + "][" + colNo + "]");
                    colNo++;
                }
                rowNo--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
