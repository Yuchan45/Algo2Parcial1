package org.uade.structure.implementation.dynamic;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.structure.definition.LinkedListADT;

public class DynamicLinkedListADT implements LinkedListADT {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(int value) {
        Node newNode = new Node(value);

        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }

        this.size++;
    }

    @Override
    public void insert(int index, int value) {
        if (index <= 0 || this.isEmpty()) {
            Node newNode = new Node(value);
            newNode.next = this.head;
            this.head = newNode;

            if (this.tail == null) {
                this.tail = newNode;
            }

            this.size++;
            return;
        }

        if (index >= this.size) {
            this.add(value);
            return;
        }

        Node previous = this.getNode(index - 1);
        Node newNode = new Node(value);
        newNode.next = previous.next;
        previous.next = newNode;
        this.size++;
    }

    @Override
    public void remove(int index) {
        this.validateIndex(index);

        if (index == 0) {
            this.head = this.head.next;

            if (this.head == null) {
                this.tail = null;
            }

            this.size--;
            return;
        }

        Node previous = this.getNode(index - 1);
        previous.next = previous.next.next;

        if (index == this.size - 1) {
            this.tail = previous;
        }

        this.size--;
    }

    @Override
    public int get(int index) {
        this.validateIndex(index);
        return this.getNode(index).value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Node getNode(int index) {
        Node current = this.head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ElementNotFoundADTException();
        }
    }

    private static class Node {
        private final int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }
}
