package com.ObjOri;

import java.io.*;
import java.util.ListIterator;

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

            // Code for Solving Maze
            int mazeRow = (numLines.getLineNumber() - 1);
            System.out.println("mazeRow: " + mazeRow);
            // instruction traversal
            int y = 0;
            // List of path thru maze
            ListIterator<Coordinates> path = null;

            // Find first instance of instruction[k] in bottommost row
            int mazeCol = 0;
            System.out.println("instructions[y]: " + instructions[y]);
            while (!(matrix[mazeRow][mazeCol].equals(instructions[y]))) {
                System.out.println(matrix[mazeRow][mazeCol] + " != " + instructions[y]);
                mazeCol++;
            }

            while (mazeRow != 0) {
                path = captureNextCoordinates(matrix, mazeCol, mazeRow, instructions[y], path);
                if (y < instructions.length) ++y;
                else y = 0;
            }

            while (path.hasNext()) {
                path.next().printCoordinates();
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

    public static ListIterator<Coordinates> captureNextCoordinates(String[][]matrix, int col, int row, String nextPath, ListIterator<Coordinates> path){
        System.out.println("Inside captureNextCoordinates");
        // Capture previous move
        int pCol = col;
        int pRow = row;

        // Check left   - m[row][col-1]
        if ((col != 0) && (matrix[row][col-1].equals(nextPath))) {
            // Create new coordinate
            Coordinates moveLeft = new Coordinates((col-1),row);
            // Verify you are not going back to previous path location
            if (path.hasPrevious()) {
                if (moveLeft != path.previous()) {
                    path.add(moveLeft);
                }
            }
            // Otherwise there was no previous path location
            else path.add(moveLeft);

            System.out.println("\tAdded moveLeft to path");
        }

        // Check up     - m[row-1][col]
        if (matrix[row-1][col].equals(nextPath)) {
            // Create new coordinate
            Coordinates moveUp = new Coordinates(col, (row+1));
            path.add(moveUp);
            // No need to verify if moveUp is previous move, move up is always progress
            System.out.println("\tAdded moveUp to path");
        }

        // Check right  - m[row][col+1]
        if (matrix[row][col+1].equals(nextPath)) {
            // Create new coordinate
            Coordinates moveRight = new Coordinates((col+1),row);
            // Verify you are not going back to previous path location
            if (path.hasPrevious()) {
                if (moveRight != path.previous()) {
                    path.add(moveRight);
                }
            }
            // Otherwise there was no previous path location
            else path.add(moveRight);
            System.out.println("\tAdded moveRight to path");
        }
        System.out.println("\tExiting captureNextCoordinates()");
        return path;
    }
}
