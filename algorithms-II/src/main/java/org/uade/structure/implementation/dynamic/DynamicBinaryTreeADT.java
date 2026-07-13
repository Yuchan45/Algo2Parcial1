package org.uade.structure.implementation.dynamic;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.BinaryTreeADT;

// Esta clase representa la implementacion dinamica del TDA Arbol Binario.
public class DynamicBinaryTreeADT implements BinaryTreeADT {

    private Node root;

    @Override
    public int getRoot() {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        return this.root.value;
    }

    @Override
    public BinaryTreeADT getLeft() {
        DynamicBinaryTreeADT result = new DynamicBinaryTreeADT();
        if (!this.isEmpty()) {
            this.copySubtreeTo(this.root.left, result);
        }

        return result;
    }

    @Override
    public BinaryTreeADT getRight() {
        DynamicBinaryTreeADT result = new DynamicBinaryTreeADT();
        if (!this.isEmpty()) {
            this.copySubtreeTo(this.root.right, result);
        }

        return result;
    }

    @Override
    public void add(int value) {
        this.root = this.add(this.root, value);
    }

    @Override
    public void remove(int value) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        if (!this.exists(value)) {
            throw new ElementNotFoundADTException();
        }

        this.root = this.remove(this.root, value);
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    private Node add(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = this.add(node.left, value);
        } else if (value > node.value) {
            node.right = this.add(node.right, value);
        }

        return node;
    }

    private Node remove(Node node, int value) {
        if (value < node.value) {
            node.left = this.remove(node.left, value);
            return node;
        }

        if (value > node.value) {
            node.right = this.remove(node.right, value);
            return node;
        }

        if (node.left == null) {
            return node.right;
        }

        if (node.right == null) {
            return node.left;
        }

        Node successor = this.minNode(node.right);
        node.value = successor.value;
        node.right = this.remove(node.right, successor.value);
        return node;
    }

    private Node minNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private boolean exists(int value) {
        Node current = this.root;
        while (current != null) {
            if (value == current.value) {
                return true;
            }

            current = value < current.value ? current.left : current.right;
        }

        return false;
    }

    private void copySubtreeTo(Node node, DynamicBinaryTreeADT tree) {
        if (node == null) {
            return;
        }

        tree.add(node.value);
        this.copySubtreeTo(node.left, tree);
        this.copySubtreeTo(node.right, tree);
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }
}
