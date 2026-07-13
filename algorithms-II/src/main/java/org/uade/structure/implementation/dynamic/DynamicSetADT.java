package org.uade.structure.implementation.dynamic;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion dinamica del TDA Conjunto.
public class DynamicSetADT implements SetADT {

    private Node head;

    @Override
    public boolean exist(int value) {
        return this.find(value) != null;
    }

    @Override
    public int choose() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.head.value;
    }

    @Override
    public void add(int value) {
        if (this.exist(value)) {
            return;
        }

        this.head = new Node(value, this.head);
    }

    @Override
    public void remove(int element) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        if (this.head.value == element) {
            this.head = this.head.next;
            return;
        }

        Node current = this.head;
        while (current.next != null && current.next.value != element) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    private Node find(int value) {
        Node current = this.head;
        while (current != null) {
            if (current.value == value) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private static class Node {
        private final int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
