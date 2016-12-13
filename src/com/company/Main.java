package com.company;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Main {


    public static void main(String[] args) {

        Matrix<IntegerNumberMutable> mtrx1 = new Matrix<>(500, 600, IntegerNumberMutable.zero()), mtrx2 = new Matrix<>(600, 500, IntegerNumberMutable.zero()), mtrx3 = null;
        mtrx1.randomize(); mtrx2.randomize();

/*        mtrx1.printFull();
        System.out.println();
        mtrx2.printFull();
        System.out.println();
*/
        try {
            mtrx3 = mtrx1.multiply(mtrx2);
        } catch (MatrixSizesMismatchException e) {
            e.printStackTrace();
        }

//        mtrx3.printFull();
//        System.out.println();

        System.out.println("Calced raw");

/*        SingleThreadMultiplier singleThreadMultiplier = new SingleThreadMultiplier(80);
        Matrix res = singleThreadMultiplier.multiply(mtrx1, mtrx2);
*/
//        res.printFull();

        ExecutorServiceMultiplier executorService = new ExecutorServiceMultiplier(16);
        Matrix res = executorService.multiply(mtrx1, mtrx2);

        boolean eq = res.equalsTo(mtrx3);
        System.out.println(eq);
    }
}
