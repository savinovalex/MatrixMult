package com.company;

/**
 * Created by suspen on 13.12.16.
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
                    SquareMatrix res = partedLeft.get(i).get(k).multiply( partedRight.get(k).get(j) );

                    //partedResult.get(i).set(j, partedResult.get(i).get(j).add(res) );

                    partedResult.get(i).get(j).addMutable(res);
                }
            }
        }



        return concatParts(mtrx1.height(), mtrx2.width(), mtrx1.getZero());
    }
}
