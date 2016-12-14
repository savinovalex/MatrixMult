package com.company;

public class Main {


    public static void main(String[] args) {

        Matrix<IntegerNumberMutable> mtrx1 = new Matrix<>(500, 600, IntegerNumberMutable.zero()), mtrx2 = new Matrix<>(600, 500, IntegerNumberMutable.zero()), mtrx3 = null;
        mtrx1.randomize(); mtrx2.randomize();

/*        mtrx1.printFull();
        System.out.println();
        mtrx2.printFull();
        System.out.println();
*/
        SimpleMultiplier simpleMultiplier = new SimpleMultiplier();
        mtrx3 = simpleMultiplier.multiply(mtrx1, mtrx2);

//        mtrx3.printFull();
//        System.out.println();

        System.out.println("Calced Simple");

/*        SingleThreadMultiplier singleThreadMultiplier = new SingleThreadMultiplier(80);
        Matrix res = singleThreadMultiplier.multiply(mtrx1, mtrx2);
*/
//        res.printFull();

        MapReduceMultiplier mapReduceMultiplier = new MapReduceMultiplier(16);
        Matrix res = mapReduceMultiplier.multiply(mtrx1, mtrx2);

        System.out.println("Calced MapReduce in " + mapReduceMultiplier.getThreadsCount() + " threads");

        boolean eq = res.equalsTo(mtrx3);
        System.out.println(eq ? "Correct" : "INCORRECT!");

        ImpatientMapReduceMultiplier impatientMapReduceMultiplier = new ImpatientMapReduceMultiplier(80);
        Matrix res2 = impatientMapReduceMultiplier.multiply(mtrx1, mtrx2);

        System.out.println("Calced ImpatientMapReduce in " + impatientMapReduceMultiplier.getThreadsCount() + " threads");
        eq = res2.equalsTo(mtrx3);
        System.out.println(eq ? "Correct" : "INCORRECT!");
    }
}
