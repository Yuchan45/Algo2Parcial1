package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.StackADT;

// Esta clase representa la implementacion estatica del TDA Pila.
public class StaticStackADT implements StackADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] values;
    private int size;

    public StaticStackADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.values[this.size - 1];
    }

    @Override
    public void add(int value) {
        if (this.size == this.values.length) {
            throw new FullADTException();
        }

        this.values[this.size] = value;
        this.size++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
