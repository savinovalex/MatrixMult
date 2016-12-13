package com.company;

import java.util.Random;

/**
 * Created by suspen on 05.12.16.
 */
public class IntegerNumber extends AbstractNumber<Integer> {

    public IntegerNumber () { this.val = 0; }
    public IntegerNumber (int v) {
        this.val = v;
    }

    @Override
    public AbstractNumber add(AbstractNumber a) {
        return new IntegerNumber(value() + a.value().intValue());
    }

    @Override
    public AbstractNumber multiply(AbstractNumber a) {
        return new IntegerNumber(value() * a.value().intValue());
    }

    @Override
    public AbstractNumber generateRandom(Random rnd) {
        return new IntegerNumber( rnd.nextInt(10) );
    }


    public static IntegerNumber zero() {
        return new IntegerNumber(0);
    }
}
