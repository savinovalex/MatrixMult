package com.company;

/**
 * Multiplier based on MapReduce approach which works in only thread.
 */
public class SingleThreadMultiplier extends AbstractPartitioningMultiplier {

    public SingleThreadMultiplier (int threadsCount) {
        super(threadsCount);
    }

    @Override
    public Matrix multiply(Matrix mtrx1, Matrix mtrx2) {
        createPartitions(mtrx1, mtrx2);

        for (int i = 0; i < resultPartedHeight; ++i) {
            for (int j = 0; j < resultPartedWidth; ++j) {
                for (int k = 0; k < partedRight.size(); ++k) {

                    SquareMatrix res = null;
                    try {
                        res = partedLeft.get(i).get(k).multiply( partedRight.get(k).get(j) );
                        partedResult.get(i).get(j).addMutable(res);
                    } catch (MatrixSizesMismatchException e) {
                        // impossible, AbstractPartitioningMultiplier.createPartitions
                    }
                }
            }
        }

        return concatParts(mtrx1.height(), mtrx2.width(), mtrx1.getZero());
    }
}
