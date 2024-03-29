package com.company;

import java.util.Random;

/**
 * Double number with mutation. All operations affects to current instance.
 */
class DoubleNumberMutable extends AbstractNumber<Double> implements NumberMutable {
    DoubleNumberMutable () { val = 0.0; }
    DoubleNumberMutable (Double i) { val = i; }

    @Override
    public AbstractNumber add(AbstractNumber n2) {
        this.val = val.doubleValue() + n2.value().doubleValue();
        return this;
    }

    @Override
    public AbstractNumber multiply(AbstractNumber n2) {
        this.val = val.doubleValue() * n2.value().doubleValue();
        return this;
    }

    @Override
    public AbstractNumber generateRandom(Random rnd) { return new DoubleNumber(rnd.nextDouble()); }

    public static DoubleNumberMutable zero() {
        return new DoubleNumberMutable(0.0);
    }
}
