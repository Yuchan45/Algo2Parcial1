package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion estatica del TDA Diccionario Multiple.
public class StaticMultipleDictionaryADT implements MultipleDictionaryADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] keys;
    private final int[] values;
    private int size;

    public StaticMultipleDictionaryADT() {
        this.keys = new int[DEFAULT_CAPACITY];
        this.values = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(int key, int value) {
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

        int index = 0;
        while (index < this.size) {
            if (this.keys[index] == key) {
                this.removeAt(index);
            } else {
                index++;
            }
        }
    }

    @Override
    public int[] get(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int count = this.countValues(key);
        if (count == 0) {
            throw new ElementNotFoundADTException();
        }

        int[] result = new int[count];
        int resultIndex = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.keys[i] == key) {
                result[resultIndex] = this.values[i];
                resultIndex++;
            }
        }

        return result;
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

    @Override
    public void remove(int key, int value) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        for (int i = 0; i < this.size; i++) {
            if (this.keys[i] == key && this.values[i] == value) {
                this.removeAt(i);
                return;
            }
        }
    }

    private int countValues(int key) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.keys[i] == key) {
                count++;
            }
        }

        return count;
    }

    private void removeAt(int index) {
        this.keys[index] = this.keys[this.size - 1];
        this.values[index] = this.values[this.size - 1];
        this.size--;
    }
}
