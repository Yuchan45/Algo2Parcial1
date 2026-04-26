package org.uade.structure.implementation.dynamic;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.StackADT;

// Esta clase representa la implementacion dinamica del TDA Pila.
public class DynamicStackADT implements StackADT {

    private Node top;

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.top.value;
    }

    @Override
    public void add(int value) {
        this.top = new Node(value, this.top);
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        this.top = this.top.next;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    private static class Node {
        private final int value;
        private final Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
