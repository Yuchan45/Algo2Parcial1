package org.uade.structure.implementation.dynamic;


import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;

// Esta clase representa la implementacion dinamica del TDA Cola con Prioridad.
public class DynamicPriorityQueueADT implements PriorityQueueADT {

    private Node head;

    @Override
    public int getElement() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.head.value;
    }

    @Override
    public int getPriority() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.head.priority;
    }

    @Override
    public void add(int value, int priority) {
        Node newNode = new Node(value, priority);

        if (this.isEmpty() || priority > this.head.priority) {
            newNode.next = this.head;
            this.head = newNode;
            return;
        }

        Node current = this.head;
        while (current.next != null && current.next.priority >= priority) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    @Override
    public void remove() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        this.head = this.head.next;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    private static class Node {
        private final int value;
        private final int priority;
        private Node next;

        private Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }
}
