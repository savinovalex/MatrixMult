package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by suspen on 03.12.16.
 */
public class Matrix<T extends AbstractNumber> {

    public Matrix(int n, int m, T zero) {
        this.zero = zero;
        matrix = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            matrix.add(new ArrayList<T>(m));
            for (int j = 0; j < m; ++j) {
                matrix.get(i).add((T)zero.clone());
            }
        }
    }

    public boolean equalsTo(Matrix mtrx) {
        return mtrx.equalsTo(matrix);
    }

    public boolean equalsTo(ArrayList<ArrayList<T>> arr) {
        if (matrix.size() != arr.size()) return false;
        if (matrix.isEmpty()) return true;
        if (matrix.get(0).size() != arr.get(0).size()) return false;

        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                if (!matrix.get(i).get(j).value().equals(arr.get(i).get(j).value()))
                    return false;
            }
        }

        return true;
    }

    public T get(int row, int col) {
        return matrix.get(row).get(col);
    }

    public void set(int row, int col, T val) {
        matrix.get(row).set(col, val);
    }

    public Matrix randomize () {

        Random rnd = new Random();

        for (int row = 0; row < height(); ++row) {
            for (int col = 0; col < width(); ++col) {
                set(row, col, (T)get(row, col).generateRandom(rnd));
            }
        }

        return this;
    }

    public Matrix extractSubmatrix(int row, int col, int rowsCount, int colsCount) {
        Matrix result = new Matrix(rowsCount, colsCount, zero);
        for (int i = row; i < height() && i < row + rowsCount; ++i) {
            for (int j = col; j < width() && j < col + colsCount; ++j) {
                result.set(i - row, j - col, get(i, j));
            }
        }

        return result;
    }

    public SquareMatrix extractSquareSubmatrix (int row, int col, int n) {
        Matrix mtrx = extractSubmatrix(row, col, n, n);

        SquareMatrix squareMtrx = null;
        try {
            squareMtrx = new SquareMatrix(mtrx, zero);
        } catch (MatrixNotSquareException e) {
            e.printStackTrace();
        }
        return squareMtrx;
    }

    public Matrix multiply(Matrix mtrx) throws MatrixSizesMismatchException {

        if (width() != mtrx.height())
            throw new MatrixSizesMismatchException(width(), height(), mtrx.width(), mtrx.height());

        Matrix result = new Matrix(height(), mtrx.width(), zero);

        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < mtrx.width(); ++j) {

                T val = (T)zero.clone();

                for (int k = 0; k < width(); ++k) {
                    T prodResult = null;
                    if (val instanceof NumberMutable) {
                        prodResult = (T)get(i, k).clone();
                        prodResult.multiply(mtrx.get(k, j));
                    } else {
                        prodResult = (T)get(i, k).multiply(mtrx.get(k, j));
                    }
                    val = (T)val.add(prodResult);
                }

                result.set(i, j, val);
            }
        }

        return result;
    }

    public Matrix add(Matrix mtrx) throws MatrixSizesMismatchException {
        Matrix result = new Matrix(height(), width(), zero);

        if (width() != mtrx.width() || height() != mtrx.height())
            throw new MatrixSizesMismatchException(height(), width(), mtrx.height(), mtrx.width());

        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                T val = (T)result.get(i, j);
                val = (T)val.add(get(i, j));
                val = (T)val.add(mtrx.get(i, j));
                result.set(i, j, val);
            }
        }

        return result;
    }

    public Matrix addMutable(Matrix mtrx) throws MatrixSizesMismatchException {
        if (width() != mtrx.width() || height() != mtrx.height())
            throw new MatrixSizesMismatchException(height(), width(), mtrx.height(), mtrx.width());

        for (int i = 0; i < height(); ++i) {
            for (int j = 0; j < width(); ++j) {
                T val = (T)get(i, j);
                val = (T)val.add(mtrx.get(i, j));
                set(i, j, val);
            }
        }

        return this;
    }

    public void printFull() {
        for (int row = 0; row < height(); ++row) {
            for (int col = 0; col < width(); ++col) {
                System.out.print(get(row, col).value());
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public int height() {
        return matrix.size();
    }

    public int width() {
        return matrix.size() > 0 ? matrix.get(0).size() : 0;
    }

    public T getZero() { return zero; }

    protected ArrayList<ArrayList<T>> matrix;
    private T zero;
}
