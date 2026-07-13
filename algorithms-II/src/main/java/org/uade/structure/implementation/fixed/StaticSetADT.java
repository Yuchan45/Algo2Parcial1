package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion estatica del TDA Conjunto.
public class StaticSetADT implements SetADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] values;
    private int size;

    public StaticSetADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean exist(int value) {
        return this.indexOf(value) != -1;
    }

    @Override
    public int choose() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.values[0];
    }

    @Override
    public void add(int value) {
        if (this.exist(value)) {
            return;
        }

        if (this.size == this.values.length) {
            throw new FullADTException();
        }

        this.values[this.size] = value;
        this.size++;
    }

    @Override
    public void remove(int element) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int index = this.indexOf(element);
        if (index == -1) {
            return;
        }

        this.values[index] = this.values[this.size - 1];
        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int indexOf(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
