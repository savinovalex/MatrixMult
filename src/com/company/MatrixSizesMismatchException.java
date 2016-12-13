package com.company;

/**
 * Created by suspen on 04.12.16.
 */
public class MatrixSizesMismatchException extends Exception {

    public MatrixSizesMismatchException (int leftHeight, int leftWidth, int rightHeight, int rightWidth) {
        this.leftHeight = leftHeight;
        this.leftWidth = leftWidth;
        this.rightHeight = rightHeight;
        this.rightWidth = rightWidth;
    }

    int leftHeight, leftWidth, rightHeight, rightWidth;
}
