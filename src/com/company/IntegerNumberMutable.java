package com.company;

import java.util.Random;

/**
 * Created by suspen on 13.12.16.
 */
class IntegerNumberMutable extends AbstractNumber<Integer> implements NumberMutable {
    IntegerNumberMutable () { val = 0; }
    IntegerNumberMutable (Integer i) { val = i; }

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
    public AbstractNumber generateRandom(Random rnd) { return new IntegerNumberMutable(rnd.nextInt(10)); }

    public static IntegerNumberMutable zero() {
        return new IntegerNumberMutable(0);
    }
}
