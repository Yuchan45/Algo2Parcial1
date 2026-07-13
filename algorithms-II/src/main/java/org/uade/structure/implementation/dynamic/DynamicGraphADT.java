package org.uade.structure.implementation.dynamic;

import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;

// Esta clase representa la implementacion dinamica del TDA Grafo.
public class DynamicGraphADT implements GraphADT {

    private VertexNode head;

    @Override
    public SetADT getVertxs() {
        SetADT result = new DynamicSetADT();
        VertexNode current = this.head;
        while (current != null) {
            result.add(current.vertex);
            current = current.next;
        }

        return result;
    }

    @Override
    public void addVertx(int vertex) {
        if (this.findVertex(vertex) != null) {
            return;
        }

        this.head = new VertexNode(vertex, this.head);
    }

    @Override
    public void removeVertx(int vertex) {
        VertexNode current = this.head;
        while (current != null) {
            this.removeEdgeFrom(current, vertex);
            current = current.next;
        }

        if (this.head == null) {
            return;
        }

        if (this.head.vertex == vertex) {
            this.head = this.head.next;
            return;
        }

        VertexNode previous = this.head;
        while (previous.next != null && previous.next.vertex != vertex) {
            previous = previous.next;
        }

        if (previous.next != null) {
            previous.next = previous.next.next;
        }
    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        this.addVertx(vertxOne);
        this.addVertx(vertxTwo);

        VertexNode vertexOne = this.findVertex(vertxOne);
        VertexNode vertexTwo = this.findVertex(vertxTwo);
        this.addOrUpdateEdge(vertexOne, vertxTwo, weight);

        if (vertxOne != vertxTwo) {
            this.addOrUpdateEdge(vertexTwo, vertxOne, weight);
        }
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {
        VertexNode vertexOne = this.findVertex(vertxOne);
        VertexNode vertexTwo = this.findVertex(vertxTwo);
        if (vertexOne == null || vertexTwo == null) {
            return;
        }

        this.removeEdgeFrom(vertexOne, vertxTwo);
        if (vertxOne != vertxTwo) {
            this.removeEdgeFrom(vertexTwo, vertxOne);
        }
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        VertexNode vertex = this.findVertex(vertxOne);
        return vertex != null && this.findEdge(vertex, vertxTwo) != null;
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        if (this.isEmpty()) {
            throw new EmptyADTException();
        }

        VertexNode vertex = this.findVertex(vertxOne);
        EdgeNode edge = vertex == null ? null : this.findEdge(vertex, vertxTwo);
        if (edge == null) {
            throw new ElementNotFoundADTException();
        }

        return edge.weight;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    private VertexNode findVertex(int vertex) {
        VertexNode current = this.head;
        while (current != null) {
            if (current.vertex == vertex) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private EdgeNode findEdge(VertexNode vertex, int destination) {
        EdgeNode current = vertex.edges;
        while (current != null) {
            if (current.destination == destination) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    private void addOrUpdateEdge(VertexNode vertex, int destination, int weight) {
        EdgeNode edge = this.findEdge(vertex, destination);
        if (edge != null) {
            edge.weight = weight;
            return;
        }

        vertex.edges = new EdgeNode(destination, weight, vertex.edges);
    }

    private void removeEdgeFrom(VertexNode vertex, int destination) {
        if (vertex.edges == null) {
            return;
        }

        if (vertex.edges.destination == destination) {
            vertex.edges = vertex.edges.next;
            return;
        }

        EdgeNode current = vertex.edges;
        while (current.next != null && current.next.destination != destination) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    private static class VertexNode {
        private final int vertex;
        private EdgeNode edges;
        private VertexNode next;

        private VertexNode(int vertex, VertexNode next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    private static class EdgeNode {
        private final int destination;
        private int weight;
        private EdgeNode next;

        private EdgeNode(int destination, int weight, EdgeNode next) {
            this.destination = destination;
            this.weight = weight;
            this.next = next;
        }
    }
}
