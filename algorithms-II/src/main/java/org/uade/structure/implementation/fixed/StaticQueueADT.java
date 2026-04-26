package org.uade.structure.implementation.fixed;

import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.QueueADT;

public class StaticQueueADT implements QueueADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] values;
    private int head;
    private int tail;
    private int size;

    public StaticQueueADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.values[this.head];
    }

    @Override
    public void add(int value) {
        if (this.size == this.values.length) {
            throw new FullADTException();
        }

        this.values[this.tail] = value;
        this.tail = (this.tail + 1) % this.values.length;
        this.size++;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        this.head = (this.head + 1) % this.values.length;
        this.size--;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
