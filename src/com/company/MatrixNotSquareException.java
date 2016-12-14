package com.company;

/**
 * Matrix Not Square
 */
public class MatrixNotSquareException extends Exception {


    protected int height, width;

    MatrixNotSquareException( int actualHeight, int actualWidth ) {
        height = actualHeight;
        width = actualWidth;
    }

    int getActualHeight () { return height; }
    int getActualWidth () { return width; }

}
