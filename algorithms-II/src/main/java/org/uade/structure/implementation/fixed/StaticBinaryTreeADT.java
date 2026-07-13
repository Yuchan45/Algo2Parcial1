package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.BinaryTreeADT;

// Esta clase representa la implementacion estatica del TDA Arbol Binario.
public class StaticBinaryTreeADT implements BinaryTreeADT {

    private static final int DEFAULT_CAPACITY = 1000;
    private static final int EMPTY_INDEX = -1;

    private final int[] values;
    private final int[] leftIndexes;
    private final int[] rightIndexes;
    private final boolean[] used;
    private int rootIndex;
    private int size;

    public StaticBinaryTreeADT() {
        this.values = new int[DEFAULT_CAPACITY];
        this.leftIndexes = new int[DEFAULT_CAPACITY];
        this.rightIndexes = new int[DEFAULT_CAPACITY];
        this.used = new boolean[DEFAULT_CAPACITY];
        this.rootIndex = EMPTY_INDEX;
        this.size = 0;

        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.leftIndexes[i] = EMPTY_INDEX;
            this.rightIndexes[i] = EMPTY_INDEX;
        }
    }

    @Override
    public int getRoot() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.values[this.rootIndex];
    }

    @Override
    public BinaryTreeADT getLeft() {
        if (this.isEmpty() || this.leftIndexes[this.rootIndex] == EMPTY_INDEX) {
            return new StaticBinaryTreeADT();
        }

        StaticBinaryTreeADT result = new StaticBinaryTreeADT();
        this.copySubtreeTo(this.leftIndexes[this.rootIndex], result);
        return result;
    }

    @Override
    public BinaryTreeADT getRight() {
        if (this.isEmpty() || this.rightIndexes[this.rootIndex] == EMPTY_INDEX) {
            return new StaticBinaryTreeADT();
        }

        StaticBinaryTreeADT result = new StaticBinaryTreeADT();
        this.copySubtreeTo(this.rightIndexes[this.rootIndex], result);
        return result;
    }

    @Override
    public void add(int value) {
        if (this.isEmpty()) {
            this.rootIndex = this.createNode(value);
            return;
        }

        int current = this.rootIndex;
        while (true) {
            if (value == this.values[current]) {
                return;
            }

            if (value < this.values[current]) {
                if (this.leftIndexes[current] == EMPTY_INDEX) {
                    this.leftIndexes[current] = this.createNode(value);
                    return;
                }

                current = this.leftIndexes[current];
            } else {
                if (this.rightIndexes[current] == EMPTY_INDEX) {
                    this.rightIndexes[current] = this.createNode(value);
                    return;
                }

                current = this.rightIndexes[current];
            }
        }
    }

    @Override
    public void remove(int value) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        if (!this.exists(value)) {
            throw new ElementNotFoundADTException();
        }

        this.rootIndex = this.remove(this.rootIndex, value);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int createNode(int value) {
        if (this.size == this.values.length) {
            throw new FullADTException();
        }

        int index = this.findFreeIndex();
        this.values[index] = value;
        this.leftIndexes[index] = EMPTY_INDEX;
        this.rightIndexes[index] = EMPTY_INDEX;
        this.used[index] = true;
        this.size++;
        return index;
    }

    private int findFreeIndex() {
        for (int i = 0; i < this.used.length; i++) {
            if (!this.used[i]) {
                return i;
            }
        }

        throw new FullADTException();
    }

    private boolean exists(int value) {
        int current = this.rootIndex;
        while (current != EMPTY_INDEX) {
            if (value == this.values[current]) {
                return true;
            }

            current = value < this.values[current] ? this.leftIndexes[current] : this.rightIndexes[current];
        }

        return false;
    }

    private int remove(int index, int value) {
        if (value < this.values[index]) {
            this.leftIndexes[index] = this.remove(this.leftIndexes[index], value);
            return index;
        }

        if (value > this.values[index]) {
            this.rightIndexes[index] = this.remove(this.rightIndexes[index], value);
            return index;
        }

        if (this.leftIndexes[index] == EMPTY_INDEX) {
            int rightIndex = this.rightIndexes[index];
            this.release(index);
            return rightIndex;
        }

        if (this.rightIndexes[index] == EMPTY_INDEX) {
            int leftIndex = this.leftIndexes[index];
            this.release(index);
            return leftIndex;
        }

        int successor = this.minIndex(this.rightIndexes[index]);
        this.values[index] = this.values[successor];
        this.rightIndexes[index] = this.remove(this.rightIndexes[index], this.values[successor]);
        return index;
    }

    private int minIndex(int index) {
        int current = index;
        while (this.leftIndexes[current] != EMPTY_INDEX) {
            current = this.leftIndexes[current];
        }

        return current;
    }

    private void release(int index) {
        this.used[index] = false;
        this.leftIndexes[index] = EMPTY_INDEX;
        this.rightIndexes[index] = EMPTY_INDEX;
        this.size--;
    }

    private void copySubtreeTo(int index, StaticBinaryTreeADT tree) {
        if (index == EMPTY_INDEX) {
            return;
        }

        tree.add(this.values[index]);
        this.copySubtreeTo(this.leftIndexes[index], tree);
        this.copySubtreeTo(this.rightIndexes[index], tree);
    }
}
