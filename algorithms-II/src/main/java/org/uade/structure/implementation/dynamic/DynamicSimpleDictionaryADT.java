package org.uade.structure.implementation.dynamic;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;

// Esta clase representa la implementacion dinamica del TDA Diccionario Simple.
public class DynamicSimpleDictionaryADT implements SimpleDictionaryADT {

    private Node head;

    @Override
    public void add(int key, int value) {
        Node node = this.find(key);
        if (node != null) {
            node.value = value;
            return;
        }

        this.head = new Node(key, value, this.head);
    }

    @Override
    public void remove(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        if (this.head.key == key) {
            this.head = this.head.next;
            return;
        }

        Node current = this.head;
        while (current.next != null && current.next.key != key) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    @Override
    public int get(int key) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        Node node = this.find(key);
        if (node == null) {
            throw new ElementNotFoundADTException();
        }

        return node.value;
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

    private Node find(int key) {
        Node current = this.head;
        while (current != null) {
            if (current.key == key) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private static class Node {
        private final int key;
        private int value;
        private Node next;

        private Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
