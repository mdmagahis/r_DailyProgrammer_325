package com.ObjOri;

public class Coordinates {
    private int row, col;

    public Coordinates (int row, int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    public void printCoordinates() {
        System.out.println("(" + col + "," + row + ")");
    }

}
