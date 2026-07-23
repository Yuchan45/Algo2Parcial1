package org.uade.algorithm.ejerciciosFinal.adelantadoOral;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.util.BinaryTreeUtil;
import org.uade.util.GraphUtil;

/**
 * AddExercise73
 * 73. Implementar un metodo que reciba un arbol binario y lo convierta en un grafo dirigido.
 */
public class AddExercise73 {
    public static void main(String[] args) {
        BinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(8);
        tree.add(3);
        tree.add(10);
        tree.add(1);
        tree.add(6);
        tree.add(14);
        tree.add(4);
        tree.add(7);
        tree.add(13);

        System.out.println("Tree In-Order:");
        BinaryTreeUtil.printInOrder(tree);

        GraphADT graph = convertTreeToGraph(tree);
        System.out.println("Graph:");
        GraphUtil.print(graph);
    }

    public static GraphADT convertTreeToGraph(BinaryTreeADT tree) {
        GraphADT graph = new StaticGraphADT();
        addTreeRelationsToGraph(tree, graph);
        return graph;
    }

    private static void addTreeRelationsToGraph(BinaryTreeADT tree, GraphADT graph) {
        if (tree.isEmpty()) {
            return;
        }

        int parent = tree.getRoot();
        graph.addVertx(parent);

        BinaryTreeADT left = tree.getLeft();
        if (!left.isEmpty()) {
            int leftValue = left.getRoot();
            graph.addVertx(leftValue);
            graph.addEdge(parent, leftValue, 1);
            addTreeRelationsToGraph(left, graph);
        }

        BinaryTreeADT right = tree.getRight();
        if (!right.isEmpty()) {
            int rightValue = right.getRoot();
            graph.addVertx(rightValue);
            graph.addEdge(parent, rightValue, 1);
            addTreeRelationsToGraph(right, graph);
        }
    }
}
