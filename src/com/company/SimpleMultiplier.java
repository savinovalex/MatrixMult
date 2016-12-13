package com.company;

/**
 * Created by suspen on 06.12.16.
 */
public class SimpleMultiplier extends AbstractMultiplier {

    @Override
    public Matrix multiply(Matrix mtrx1, Matrix mtrx2) {

        try {
            return mtrx1.multiply(mtrx2);
        } catch (MatrixSizesMismatchException e) {
            e.printStackTrace();
        }

        return null;
    }
}
