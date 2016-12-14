package com.company;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Abstraction for methods that would use MapReduce approach.
 */
abstract public class AbstractPartitioningMultiplier extends AbstractMultiplier {


    protected int threadsCount;
    protected ArrayList<ArrayList<SquareMatrix>> partedLeft, partedRight, partedResult;
    protected int resultPartedHeight, resultPartedWidth, squareSize;


    public AbstractPartitioningMultiplier (int threadsCount) {
        this.threadsCount = threadsCount;
    }

    /**
     * We need to split both matrices on square part of the same size and the same parts count for each dimension.
     * Based on threadsCount we can calculate optimal size of square.
     * The most right and the most bottom squares can be partially out of the borders of matrices. In this case
     * out-of-range values filled with zeros.
     *
     * @param mtrx1
     * @param mtrx2
     */
    protected void createPartitions (Matrix mtrx1, Matrix mtrx2) {

        int sqrt = Math.round((float)Math.max(1.0, Math.sqrt((double)threadsCount)));
        squareSize = (mtrx1.width() - 1) / sqrt + 1;

        partedLeft = createPartition(mtrx1, squareSize);
        partedRight = createPartition(mtrx2, squareSize);

        resultPartedHeight = (mtrx1.height() - 1) / squareSize + 1;
        resultPartedWidth = (mtrx2.width() - 1) / squareSize + 1;
        partedResult = new ArrayList<>();
        for (int i = 0; i < resultPartedHeight; ++i) {
            partedResult.add(new ArrayList<>());
            for (int j = 0; j < resultPartedWidth; ++j) {
                partedResult.get(i).add(new SquareMatrix(squareSize, mtrx1.getZero()));
            }
        }
    }

    /**
     * After calculation in array partedResult we have multiplied matrix splitted on square parts.
     * So we to concatenate all values that are not out of the borders.
     * @param rows
     * @param cols
     * @param zero
     * @return
     */
    protected Matrix concatParts (int rows, int cols, AbstractNumber zero) {
        Matrix result = new Matrix(rows, cols, zero);

        for (int i = 0; i < resultPartedHeight; ++i) {
            for (int j = 0; j < resultPartedWidth; ++j) {
                for (int dr = 0; dr < squareSize; ++dr) {
                    for (int dc = 0; dc < squareSize; ++dc) {
                        int row = i * squareSize + dr;
                        int col = j * squareSize + dc;

                        if (!(row < rows && col < cols)) continue;

                        result.set(row, col, partedResult.get(i).get(j).get(dr, dc));
                    }
                }
            }
        }

        return result;
    }

    private ArrayList<ArrayList<SquareMatrix>> createPartition (Matrix mtrx, int squareSize) {
        ArrayList<ArrayList<SquareMatrix>> result = new ArrayList<>();
        int n = (mtrx.height() - 1) / squareSize + 1, m = (mtrx.width() - 1) / squareSize + 1;

        for (int rowI = 0; rowI < n; ++rowI) {
            result.add(new ArrayList<>());
            for (int colI = 0; colI < m; ++colI) {
                result.get(rowI).add( mtrx.extractSquareSubmatrix(rowI * squareSize, colI * squareSize, squareSize) );
            }
        }

        return result;
    }

    private class PairedParts {
        public PairedParts(SquareMatrix left, SquareMatrix right) {
            this.left = left;
            this.right = right;
            this.index = 0;
        }

        public SquareMatrix left() { return left; }
        public SquareMatrix right() { return right; }
        public int index() { return index; }

        private final SquareMatrix left, right;
        private final int index;
    }

    public int getThreadsCount () { return threadsCount; }

}
