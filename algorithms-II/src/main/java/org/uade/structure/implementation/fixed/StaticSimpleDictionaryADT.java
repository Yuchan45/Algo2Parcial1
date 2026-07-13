package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;

// Esta clase representa la implementacion estatica del TDA Diccionario Simple.
public class StaticSimpleDictionaryADT implements SimpleDictionaryADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] keys;
    private final int[] values;
    private int size;

    public StaticSimpleDictionaryADT() {
        this.keys = new int[DEFAULT_CAPACITY];
        this.values = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(int key, int value) {
        int index = this.indexOf(key);
        if (index != -1) {
            this.values[index] = value;
            return;
        }

        if (this.size == this.keys.length) {
            throw new FullADTException();
        }

        this.keys[this.size] = key;
        this.values[this.size] = value;
        this.size++;
    }

    @Override
    public void remove(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int index = this.indexOf(key);
        if (index == -1) {
            return;
        }

        this.keys[index] = this.keys[this.size - 1];
        this.values[index] = this.values[this.size - 1];
        this.size--;
    }

    @Override
    public int get(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int index = this.indexOf(key);
        if (index == -1) {
            throw new ElementNotFoundADTException();
        }

        return this.values[index];
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new StaticSetADT();
        for (int i = 0; i < this.size; i++) {
            result.add(this.keys[i]);
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int indexOf(int key) {
        for (int i = 0; i < this.size; i++) {
            if (this.keys[i] == key) {
                return i;
            }
        }

        return -1;
    }
}
