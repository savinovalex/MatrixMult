package com.company;

import java.util.Random;

/**
 * Created by suspen on 05.12.16.
 */
abstract public class AbstractNumber<T extends Number> implements Cloneable {

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

    protected T val;
}
