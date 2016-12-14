package com.company;

import java.util.ArrayList;

/**
 * Special case for square matrices (N x N sizes). Used in MapReduce approach.
 */
public class SquareMatrix<T extends AbstractNumber> extends Matrix {

    public SquareMatrix(Matrix mtrx, T zero) throws MatrixNotSquareException {
        super(0, 0, zero);
        matrix = mtrx.matrix;

        if (height() != width()) throw new MatrixNotSquareException(height(), width());
    }

    public SquareMatrix(int n, T zero) {
        super(n, n, zero);
    }

    public SquareMatrix multiply(SquareMatrix mtrx) throws MatrixSizesMismatchException {
        try {
            SquareMatrix result = new SquareMatrix(super.multiply(mtrx), getZero());
            return result;
        } catch (MatrixNotSquareException e) { // both are squares, impossible
        }

        return null;
    }

    public SquareMatrix add(SquareMatrix mtrx) throws MatrixSizesMismatchException {
        try {
            SquareMatrix result = new SquareMatrix(super.add(mtrx), getZero());
            return result;
        } catch (MatrixNotSquareException e) { // bot are squares, impossible
        }

        return null;
    }

    public SquareMatrix addMutable(SquareMatrix mtrx) throws MatrixSizesMismatchException {

        super.addMutable(mtrx);

        return this;
    }

}