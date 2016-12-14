package com.company;

import java.util.Random;

/**
 * Abstraction for any number which could be the member of matrices.
 */
abstract public class AbstractNumber<T extends Number> implements Cloneable {

    protected T val;

    abstract public AbstractNumber add (AbstractNumber a);
    abstract public AbstractNumber multiply (AbstractNumber a);
    abstract public AbstractNumber generateRandom (Random rnd);

    public T value() { return val; }

    @Override
    public Object clone () {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
