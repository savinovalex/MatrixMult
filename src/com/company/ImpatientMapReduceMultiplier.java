package com.company;

import java.util.concurrent.*;

/**
 * Created by suspen on 14.12.16.
 */
public class ImpatientMapReduceMultiplier extends AbstractPartitioningMultiplier {

    private Phaser phaser;



    public ImpatientMapReduceMultiplier(int threadsCount) {
        super(threadsCount);
    }

    @Override
    public Matrix multiply(Matrix mtrx1, Matrix mtrx2) {
        createPartitions(mtrx1, mtrx2);

        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        ExecutorCompletionService completionService = new ExecutorCompletionService(executorService);


        phaser = new Phaser ();

        for (int i = 0; i < resultPartedHeight; ++i) {
            for (int j = 0; j < resultPartedWidth; ++j) {
                for (int k = 0; k < partedRight.size(); ++k) {
                    ImpatientMapReduceMultiplier.Worker worker = new ImpatientMapReduceMultiplier.Worker(i, j, k);
                    completionService.submit(worker);

                    phaser.register();
                }
            }
        }



        phaser.register();
        phaser.arriveAndAwaitAdvance();

        executorService.shutdown();

        return concatParts(mtrx1.height(), mtrx2.width(), mtrx1.getZero());
    }

    private class Worker implements Callable<Matrix> {

        public Worker (int row, int col, int k) {
            this.row = row;
            this.col = col;
            this.k = k;
        }

        @Override
        public Matrix call() throws Exception {
            SquareMatrix res = partedLeft.get(row).get(k).multiply( partedRight.get(k).get(col) );

            synchronized (partedResult.get(row).get(col)) {
                partedResult.get(row).get(col).addMutable(res);
            }


            phaser.arrive();

            return partedResult.get(row).get(col);
        }

        private final int row, col, k;
    }
}
