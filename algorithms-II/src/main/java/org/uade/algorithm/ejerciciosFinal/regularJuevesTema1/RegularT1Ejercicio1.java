package org.uade.algorithm.ejerciciosFinal.regularJuevesTema1;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.BinaryTreeUtil;
import org.uade.util.GraphUtil;
import org.uade.util.StackADTUtil;

/**
 * RegularEjercicio1
 * Dadas una stack y un grafo, devolver un árbol con los elementos en común e
 * imprimir los árboles por nivel.
 */
public class RegularT1Ejercicio1 {
    public static void main(String[] args) {
        // Init
        StackADT stack = new StaticStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println("Stack:");
        StackADTUtil.print(stack);

        System.out.println("");
        GraphADT graph = new StaticGraphADT();
        graph.addVertx(1);
        graph.addVertx(2);
        graph.addVertx(3);
        graph.addVertx(4);
        graph.addVertx(5);
        System.out.println("Graph:");
        GraphUtil.print(graph);

        // Process
        SetADT verts = graph.getVertxs();
        BinaryTreeADT resultTree = getCommonElements(stack, graph, verts);
        System.out.println("");
        System.out.println("Result Tree with common elements:");
        
        // Agregar el print de "level-order"
        BinaryTreeUtil.printInOrder(resultTree);

    }

    public static BinaryTreeADT getCommonElements(StackADT stack, GraphADT graph, SetADT verts) {
        BinaryTreeADT tree = new StaticBinaryTreeADT();

        StackADT copyStack = StackADTUtil.copy(stack);
        while (!copyStack.isEmpty()) {
            int element = copyStack.getElement();
           if (verts.exist(element)) {
            tree.add(element);
           }
           copyStack.remove();
        }

        return tree;
    }
}
