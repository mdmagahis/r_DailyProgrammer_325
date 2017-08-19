package com.ObjOri;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Get size of maze, aka number of lines
        LineNumberReader numLines = new LineNumberReader(new FileReader("resources/input1.txt"));
        numLines.skip(Long.MAX_VALUE);

        // Create matrix with length and width of input maze
        String[][] matrix = new String[numLines.getLineNumber()][numLines.getLineNumber()];

        BufferedReader inputFile = new BufferedReader(new FileReader("resources/input1.txt"));
        String s;
        String instructLine;

        int line = numLines.getLineNumber();
        System.out.println("Number of lines: " + line);

        try {
            // Read first line (instruction line)
            instructLine = inputFile.readLine();
            String[] instructions = instructLine.split(" ");
            System.out.println("length of instruction: " + instructions.length);
            for (int i = 0; i < instructions.length; i++) {
                System.out.println("Instruction " + i + ": " + instructions[i]);
            }

            // Write contents of input to matrix
            int rowNo = 0;

            while ((s = inputFile.readLine()) != null) {
                String[] tokens = s.split(" ");
                int colNo = 0;

                for (String c : tokens) {
                    // Write line char-by-char, ignoring spaces
                    matrix[rowNo][colNo] = c;
                    System.out.println(c + " written to matrix[" + rowNo + "][" + colNo + "]");
                    colNo++;
                }
                rowNo++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Debugging output
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println(" -- row " + i);
//        }
//        System.out.println("---------------------");
//        System.out.println("0 1 2 3 4  -- columns");
    }
}
