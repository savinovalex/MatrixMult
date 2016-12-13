package com.company;

import java.util.Random;

/**
 * Created by suspen on 13.12.16.
 */
public class DoubleNumber extends AbstractNumber<Double> {

    public DoubleNumber () { this.val = 0.0; }
    public DoubleNumber (double v) {
        this.val = v;
    }


    @Override
    public AbstractNumber add(AbstractNumber a) {
        return new DoubleNumber(value() + a.value().doubleValue());
    }

    @Override
    public AbstractNumber multiply(AbstractNumber a) {
        return new DoubleNumber(value() * a.value().doubleValue());
    }

    @Override
    public AbstractNumber generateRandom(Random rnd) {
        return new DoubleNumber(rnd.nextDouble());
    }

    public static DoubleNumber zero() {
        return new DoubleNumber(0.0);
    }
}
