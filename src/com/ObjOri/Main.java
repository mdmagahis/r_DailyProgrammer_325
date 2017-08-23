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

        try {
            // Read first line (instruction line)
            instructLine = inputFile.readLine();
            String[] instructions = instructLine.split(" ");

            // Write contents of input to matrix
            int rowNo = 0;

            while ((s = inputFile.readLine()) != null) {
                String[] tokens = s.split(" ");
                int colNo = 0;

                for (String c : tokens) {
                    // Write line char-by-char, ignoring spaces
                    matrix[rowNo][colNo] = c;
                    colNo++;
                }
                rowNo++;
            }

            // Code for Solving Maze
            int mazeRow = (numLines.getLineNumber() - 1);
            // instruction traversal
            int y = 0;
            // List of path thru maze
            LinkedList<Coordinates> path = new LinkedList<Coordinates>();
            ListIterator<Coordinates> li = path.listIterator(0);

            // Find first instance of instruction[k] in bottommost row
            int mazeCol = 0;
            while (!(matrix[mazeRow][mazeCol].equals(instructions[y]))) {
                mazeCol++;
            }

            Coordinates start = new Coordinates(mazeRow, mazeCol);
            li.add(start);

            while (mazeRow != 0) {
                if (y < instructions.length-1) ++y;
                else y = 0;

                li = captureNextCoordinates(matrix, mazeCol, mazeRow, instructions[y], li);

                // Update to current mazeCol and mazeRow
                mazeCol = li.previous().getCol();
                li.next();
                mazeRow = li.previous().getRow();
                li.next();
            }

            // Get back to beginning
            while (li.hasPrevious()) li.previous();
            while (li.hasNext()) {
                li.next().printCoordinates();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListIterator<Coordinates> captureNextCoordinates(String[][]matrix, int col, int row, String nextPath, ListIterator<Coordinates> path){
        Coordinates moveLeft = null;
        Coordinates moveUp = null;
        Coordinates moveRight = null;

        while (path.hasNext()) path.next();

        Coordinates prevPath = null;
        if (path.hasPrevious()) {
            path.previous();
            if (path.hasPrevious()) {
                prevPath = path.previous();
                path.next();
            }
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
                && (!moveLeft.equals(prevPath))) {
            // Create new coordinate
            moveLeft = new Coordinates(row, (col-1));
            // Verify you are not going back to previous path location
            path.add(moveLeft);
        }

        // Check up     - m[row-1][col]
        else if (matrix[row-1][col].equals(nextPath)) {
            // Create new coordinate
            path.add(moveUp);
            // No need to verify if moveUp is previous move, move up is always progress
        }

        // Check right  - m[row][col+1]
        else if (matrix[row][col+1].equals(nextPath)
                && (!moveRight.equals(prevPath))) {
            // Create new coordinate
            moveRight = new Coordinates(row, (col+1));
            // Verify you are not going back to previous path location
            path.add(moveRight);
        }
        return path;
    }
}
