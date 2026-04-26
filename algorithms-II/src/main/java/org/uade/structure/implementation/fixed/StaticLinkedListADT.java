package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.LinkedListADT;

public class StaticLinkedListADT implements LinkedListADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] values;
    private int size;

    public StaticLinkedListADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(int value) {
        this.ensureCapacity();
        this.values[this.size] = value;
        this.size++;
    }

    @Override
    public void insert(int index, int value) {
        this.ensureCapacity();

        if (index <= 0) {
            index = 0;
        } else if (index >= this.size) {
            index = this.size;
        }

        for (int i = this.size; i > index; i--) {
            this.values[i] = this.values[i - 1];
        }

        this.values[index] = value;
        this.size++;
    }

    @Override
    public void remove(int index) {
        this.validateIndex(index);

        for (int i = index; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
        }

        this.size--;
    }

    @Override
    public int get(int index) {
        this.validateIndex(index);
        return this.values[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void ensureCapacity() {
        if (this.size == this.values.length) {
            throw new FullADTException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ElementNotFoundADTException();
        }
    }
}
