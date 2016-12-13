package com.company;

import java.util.ArrayList;

/**
 * Created by suspen on 03.12.16.
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

    public SquareMatrix multiply(SquareMatrix mtrx) {
        try {
            SquareMatrix result = new SquareMatrix(super.multiply(mtrx), getZero());
            return result;
        } catch (MatrixSizesMismatchException e) {
        } catch (MatrixNotSquareException e) {
        }

        return null;
    }

    public SquareMatrix add(SquareMatrix mtrx) {
        try {
            SquareMatrix result = new SquareMatrix(super.add(mtrx), getZero());
            return result;
        } catch (MatrixSizesMismatchException e) {
            e.printStackTrace();
        } catch (MatrixNotSquareException e) {
        }

        return null;
    }

    public SquareMatrix addMutable(SquareMatrix mtrx) {
        try {
            super.addMutable(mtrx);
        } catch (MatrixSizesMismatchException e) {
            e.printStackTrace();
        }

        return this;
    }

}