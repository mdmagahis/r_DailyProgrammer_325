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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coordinates)) {
            return false;
        }

        Coordinates that = (Coordinates) other;

        return (this.col == that.col)
                && (this.row == that.row);
    }
}
