package com.ObjOri;

import java.io.*;
import java.util.LinkedList;
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
            LinkedList<Coordinates> path = new LinkedList<Coordinates>();
            ListIterator<Coordinates> li = path.listIterator(0);

            // Find first instance of instruction[k] in bottommost row
            int mazeCol = 0;
            System.out.println("instructions[y]: " + instructions[y]);
            while (!(matrix[mazeRow][mazeCol].equals(instructions[y]))) {
                System.out.println(matrix[mazeRow][mazeCol] + " != " + instructions[y]);
                mazeCol++;
            }

            Coordinates start = new Coordinates(mazeRow, mazeCol);
            start.printCoordinates();
            System.out.println("\tAdded start to part");
            li.add(start);

            System.out.println("Entering while loop...");
            while (mazeRow != 0) {
                System.out.print("Previous Coordinates:\t\t\t\t\t");
                li.previous().printCoordinates();
                li.next();

                if (y < instructions.length-1) ++y;
                else y = 0;
                System.out.println("\tCurrent instruction: " + instructions[y]);

                li = captureNextCoordinates(matrix, mazeCol, mazeRow, instructions[y], li);

                System.out.print("Next coordinates added: ");
                li.previous().printCoordinates();
                li.next();
                mazeCol = li.previous().getCol();
                li.next();
                mazeRow = li.previous().getRow();
                li.next();

                System.out.println("* -- End of while() -- * ");
            }

            // Get back to beginning
            while (li.hasPrevious()) li.previous();
            while (li.hasNext()) {
                li.next().printCoordinates();
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
        System.out.println("\t\tInside captureNextCoordinates");
        System.out.println("\t\tcol = " + col + ", row = " + row);

        Coordinates moveLeft = null;
        Coordinates moveUp = null;
        Coordinates moveRight = null;

        while (path.hasNext()) path.next();

        Coordinates prevPath = null;
        if (path.hasPrevious()) {
            prevPath = path.previous();
            path.next();
        }

        if (col != 0) {
            moveLeft = new Coordinates(row, (col-1));
        }
        moveUp = new Coordinates((row-1), col);
        if (row != matrix.length) {
            moveRight = new Coordinates(row, (col+1));
        }

        // Check left   - m[row][col-1]
        if ((matrix[row][col-1].equals(nextPath))
                && (!(moveLeft.equals(prevPath)))) {
            // Create new coordinate
            moveLeft = new Coordinates(row, (col-1));
            // Verify you are not going back to previous path location
            path.add(moveLeft);

            System.out.print("\t\tAdded moveLeft to path: ");
            moveLeft.printCoordinates();
        }

        // Check up     - m[row-1][col]
        else if (matrix[row-1][col].equals(nextPath)) {
            // Create new coordinate
            path.add(moveUp);
            // No need to verify if moveUp is previous move, move up is always progress
            System.out.print("\t\tAdded moveUp to path: ");
            moveUp.printCoordinates();
        }

        // Check right  - m[row][col+1]
        else if (matrix[row][col+1].equals(nextPath)
                && (!(moveRight.equals(prevPath)))) {
            // Create new coordinate
            moveRight = new Coordinates(row, (col+1));
            // Verify you are not going back to previous path location
            path.add(moveRight);
            System.out.print("\t\tAdded moveRight to path: ");
            moveRight.printCoordinates();
        }
        System.out.println("\tExiting captureNextCoordinates()");
        return path;
    }
}
