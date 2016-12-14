package com.company;

import java.util.concurrent.*;

/**
 * Multiplier based on ExecutorService.
 */
public class ExecutorServiceMultiplier extends AbstractPartitioningMultiplier {

    private Phaser phaser;



    public ExecutorServiceMultiplier(int threadsCount) {
        super(threadsCount);
    }

    @Override
    public Matrix multiply(Matrix mtrx1, Matrix mtrx2) {
        createPartitions(mtrx1, mtrx2);

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        ExecutorCompletionService completionService = new ExecutorCompletionService(executorService);

        for (int i = 0; i < resultPartedHeight; ++i) {
            for (int j = 0; j < resultPartedWidth; ++j) {
                Worker worker = new Worker(i, j);
                completionService.submit(worker);
            }
        }

        phaser = new Phaser (resultPartedHeight * resultPartedWidth);

        phaser.register();
        phaser.arriveAndAwaitAdvance();

        executorService.shutdown();

        return concatParts(mtrx1.height(), mtrx2.width(), mtrx1.getZero());
    }

    private class Worker implements Callable<Matrix> {

        public Worker (int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public Matrix call() throws Exception {
            for (int k = 0; k < partedRight.size(); ++k) {
                SquareMatrix res = partedLeft.get(row).get(k).multiply( partedRight.get(k).get(col) );

                partedResult.get(row).get(col).addMutable(res);
            }

            phaser.arrive();

            return partedResult.get(row).get(col);
        }

        private final int row, col;
    }
}
