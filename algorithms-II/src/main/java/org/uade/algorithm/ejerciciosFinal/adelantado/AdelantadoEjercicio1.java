package org.uade.algorithm.ejerciciosFinal.adelantado;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.util.GraphUtil;

/**
 * AdelantadoEjercicio1
 * Dar una cola de elementos en común entre un grafo y un árbol.
 */
public class AdelantadoEjercicio1 {
    public static void main(String[] args) {
        // Set up
        GraphADT graph = new StaticGraphADT();
        graph.addVertx(1);
        graph.addVertx(2);
        graph.addVertx(3);
        graph.addVertx(4);
        graph.addVertx(5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 1);
        graph.addEdge(4, 5, 1);
        GraphUtil.print(graph);

        BinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(3);
        tree.add(4);
        tree.add(5);
        printInOrderRecursive(tree);
        System.out.println("");
        System.out.println("");


        // Process
        QueueADT resultQueue = new StaticQueueADT();
        getTreeAndGraphCommonElements(resultQueue, tree, graph);
        System.out.println("Result Queue:");
        printQueue(resultQueue);

    }

    public static void printInOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        printInOrderRecursive(tree.getLeft());
        System.out.print(tree.getRoot() + " ");
        printInOrderRecursive(tree.getRight());
    }

    public static void getTreeAndGraphCommonElements(QueueADT resultQueue, BinaryTreeADT tree, GraphADT graph) {
        if (tree.isEmpty()) {
            return;
        }

        int currentValue = tree.getRoot();
        SetADT verts = graph.getVertxs();

        getTreeAndGraphCommonElements(resultQueue, tree.getLeft(), graph);
        // System.out.print(currentValue + " ");
        if (verts.exist(currentValue)) {
            resultQueue.add(currentValue);
        }
        getTreeAndGraphCommonElements(resultQueue, tree.getRight(), graph);
    }

    

    public static QueueADT copyQueue(QueueADT queue) {
        QueueADT copy = new StaticQueueADT();
        QueueADT aux = new StaticQueueADT();

        while (!queue.isEmpty()) {
            int element = queue.getElement();
            copy.add(element);
            aux.add(element);
            queue.remove();
        }

        while (!aux.isEmpty()) {
            int element = aux.getElement();
            queue.add(element);
            aux.remove();
        }

        return copy;
    }

    public static void printQueue(QueueADT queue) {
        QueueADT copy = copyQueue(queue);

        while (!copy.isEmpty()) {
            int element = copy.getElement();
            System.out.print(element + ", ");
            copy.remove();
        }
    }

}
