package com.company;

/**
 * Matrix Sizes Mismatch
 */
public class MatrixSizesMismatchException extends Exception {


    protected int leftHeight, leftWidth, rightHeight, rightWidth;

    public MatrixSizesMismatchException (int leftHeight, int leftWidth, int rightHeight, int rightWidth) {
        this.leftHeight = leftHeight;
        this.leftWidth = leftWidth;
        this.rightHeight = rightHeight;
        this.rightWidth = rightWidth;
    }

    public int getLeftHeight() { return leftHeight; }
    public int getLeftWidth() { return leftWidth; }
    public int getRightHeight() { return rightHeight; }
    public int getRightWidth() { return rightWidth; }

}
