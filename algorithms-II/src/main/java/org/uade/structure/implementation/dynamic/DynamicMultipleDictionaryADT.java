package org.uade.structure.implementation.dynamic;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion dinamica del TDA Diccionario Multiple.
public class DynamicMultipleDictionaryADT implements MultipleDictionaryADT {

    private Node head;

    @Override
    public void add(int key, int value) {
        this.head = new Node(key, value, this.head);
    }

    @Override
    public void remove(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        while (this.head != null && this.head.key == key) {
            this.head = this.head.next;
        }

        Node current = this.head;
        while (current != null && current.next != null) {
            if (current.next.key == key) {
                current.next = current.next.next;
            } else {
                current = current.next;
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
        Node current = this.head;
        while (current != null) {
            if (current.key == key) {
                result[resultIndex] = current.value;
                resultIndex++;
            }

            current = current.next;
        }

        return result;
    }

    @Override
    public SetADT getKeys() {
        SetADT result = new DynamicSetADT();
        Node current = this.head;
        while (current != null) {
            result.add(current.key);
            current = current.next;
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void remove(int key, int value) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        if (this.head.key == key && this.head.value == value) {
            this.head = this.head.next;
            return;
        }

        Node current = this.head;
        while (current.next != null) {
            if (current.next.key == key && current.next.value == value) {
                current.next = current.next.next;
                return;
            }

            current = current.next;
        }
    }

    private int countValues(int key) {
        int count = 0;
        Node current = this.head;
        while (current != null) {
            if (current.key == key) {
                count++;
            }

            current = current.next;
        }

        return count;
    }

    private static class Node {
        private final int key;
        private final int value;
        private Node next;

        private Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
