package org.uade.structure.implementation.fixed;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.exception.FullADTException;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion estatica del TDA Grafo.
public class StaticGraphADT implements GraphADT {

    private static final int DEFAULT_CAPACITY = 1000;

    private final int[] vertices;
    private final boolean[][] edges;
    private final int[][] weights;
    private int size;

    public StaticGraphADT() {
        this.vertices = new int[DEFAULT_CAPACITY];
        this.edges = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.weights = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public SetADT getVertxs() {
        SetADT result = new StaticSetADT();
        for (int i = 0; i < this.size; i++) {
            result.add(this.vertices[i]);
        }

        return result;
    }

    @Override
    public void addVertx(int vertex) {
        if (this.indexOf(vertex) != -1) {
            return;
        }

        if (this.size == this.vertices.length) {
            throw new FullADTException();
        }

        this.vertices[this.size] = vertex;
        this.size++;
    }

    @Override
    public void removeVertx(int vertex) {
        int index = this.indexOf(vertex);
        if (index == -1) {
            return;
        }

        int last = this.size - 1;
        this.vertices[index] = this.vertices[last];

        for (int i = 0; i < this.size; i++) {
            this.edges[index][i] = this.edges[last][i];
            this.weights[index][i] = this.weights[last][i];
            this.edges[i][index] = this.edges[i][last];
            this.weights[i][index] = this.weights[i][last];
        }

        this.edges[index][index] = this.edges[last][last];
        this.weights[index][index] = this.weights[last][last];
        this.clearIndex(last);
        this.size--;
    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        this.addVertx(vertxOne);
        this.addVertx(vertxTwo);

        int indexOne = this.indexOf(vertxOne);
        int indexTwo = this.indexOf(vertxTwo);
        this.edges[indexOne][indexTwo] = true;
        this.weights[indexOne][indexTwo] = weight;
        this.edges[indexTwo][indexOne] = true;
        this.weights[indexTwo][indexOne] = weight;
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        int indexOne = this.indexOf(vertxOne);
        int indexTwo = this.indexOf(vertxTwo);
        if (indexOne == -1 || indexTwo == -1) {
            return;
        }

        this.edges[indexOne][indexTwo] = false;
        this.weights[indexOne][indexTwo] = 0;
        this.edges[indexTwo][indexOne] = false;
        this.weights[indexTwo][indexOne] = 0;
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int indexOne = this.indexOf(vertxOne);
        int indexTwo = this.indexOf(vertxTwo);
        return indexOne != -1 && indexTwo != -1 && this.edges[indexOne][indexTwo];
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        int indexOne = this.indexOf(vertxOne);
        int indexTwo = this.indexOf(vertxTwo);
        if (indexOne == -1 || indexTwo == -1 || !this.edges[indexOne][indexTwo]) {
            throw new ElementNotFoundADTException();
        }

        return this.weights[indexOne][indexTwo];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int indexOf(int vertex) {
        for (int i = 0; i < this.size; i++) {
            if (this.vertices[i] == vertex) {
                return i;
            }
        }

        return -1;
    }

    private void clearIndex(int index) {
        this.vertices[index] = 0;
        for (int i = 0; i < this.edges.length; i++) {
            this.edges[index][i] = false;
            this.edges[i][index] = false;
            this.weights[index][i] = 0;
            this.weights[i][index] = 0;
        }
    }
}
