package com.company;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger number with mutation. All operations affects to current instance.
 */
public class IntegerAtomicNumberMutable<AtomicInteger> extends AbstractNumber {
    IntegerAtomicNumberMutable () { val = 0; }
    IntegerAtomicNumberMutable (Integer i) { val = i; }

    @Override
    public AbstractNumber add(AbstractNumber n2) {
        this.val = val.intValue() + n2.value().intValue();
        return this;
    }

    @Override
    public AbstractNumber multiply(AbstractNumber n2) {
        this.val = val.intValue() * n2.value().intValue();
        return this;
    }

    @Override
    public AbstractNumber generateRandom(Random rnd) { return new IntegerAtomicNumberMutable(rnd.nextInt(10)); }

    public static IntegerAtomicNumberMutable zero () {
        return new IntegerAtomicNumberMutable(0);
    }
}
