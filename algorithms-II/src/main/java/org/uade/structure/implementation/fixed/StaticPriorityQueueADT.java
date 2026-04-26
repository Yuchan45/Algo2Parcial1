package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.PriorityQueueADT;

public class StaticPriorityQueueADT implements PriorityQueueADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] values;
    private final int[] priorities;
    private int size;

    public StaticPriorityQueueADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.priorities = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.values[0];
    }

    @Override
    public int getPriority() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.priorities[0];
    }

    @Override
    public void add(int value, int priority) {
        if (this.size == this.values.length) {
            throw new FullADTException();
        }

        int index = 0;
        while (index < this.size && this.priorities[index] >= priority) {
            index++;
        }

        for (int i = this.size; i > index; i--) {
            this.values[i] = this.values[i - 1];
            this.priorities[i] = this.priorities[i - 1];
        }

        this.values[index] = value;
        this.priorities[index] = priority;
        this.size++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        for (int i = 0; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
            this.priorities[i] = this.priorities[i + 1];
        }

        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
