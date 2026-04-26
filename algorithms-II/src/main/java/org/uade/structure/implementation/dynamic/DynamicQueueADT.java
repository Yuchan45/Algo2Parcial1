package org.uade.structure.implementation.dynamic;


import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.QueueADT;

// Esta clase representa la implementacion dinamica del TDA Cola.
public class DynamicQueueADT implements QueueADT {

    private Node first;
    private Node last;

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.first.value;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);

        if (this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
            return;
        }

        this.last.next = newNode;
        this.last = newNode;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        this.first = this.first.next;

        if (this.first == null) {
            this.last = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    private static class Node {
        private final int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }
}
