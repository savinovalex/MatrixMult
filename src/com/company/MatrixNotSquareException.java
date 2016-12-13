package com.company;

/**
 * Created by suspen on 04.12.16.
 */
public class MatrixNotSquareException extends Exception {

    MatrixNotSquareException( int actualHeight, int actualWidth ) {
        height = actualHeight;
        width = actualWidth;
    }

    int getActualHeight () { return height; }
    int getActualWidth () { return width; }

    protected int height, width;
}
