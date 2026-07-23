package org.uade.algorithm.ejerciciosFinal.regularJuevesTema2;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;


/**
 * RegularT2Ejercicio1
 * Dados un árbol y un diccionario, devolver un grafo con los elementos en común
 */
public class RegularT2Ejercicio1 {
    public static void main(String[] args) {
        // Init
        StaticSimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 10);
        dict.add(2, 20);
        dict.add(3, 30);
        dict.add(4, 40);
        dict.add(5, 50);

        StaticBinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(2);
        tree.add(3);
        tree.add(4);

        printDict(dict, "Dictionary: ");
        printTreeInOrder(tree, "Tree In-Order: ");

        // Process
        // Deberia devolver un grafo con los elementos: [20, 30, 40] que son los valores de las keys en comun (2, 3, 4)
        GraphADT result = getTreeAndDictCommonElements(dict, tree);
        printGraph(result, "Result graph: ");
    }

    public static GraphADT getTreeAndDictCommonElements(SimpleDictionaryADT dict, BinaryTreeADT tree) {
        GraphADT result = new StaticGraphADT();

        iterateTree(dict, tree, result);
        return result;
    }
    public static void iterateTree(SimpleDictionaryADT dict, BinaryTreeADT tree, GraphADT result) {
        if (tree.isEmpty()) {
            return;
        }
        
        SetADT keys = dict.getKeys();
        int currentValue = tree.getRoot();
        if (keys.exist(currentValue)) {
            result.addVertx(dict.get(currentValue));
        }

        iterateTree(dict, tree.getLeft(), result);
        iterateTree(dict, tree.getRight(), result);
    }


    public static void printDict(SimpleDictionaryADT dict, String title) {
        SetADT keys = dict.getKeys();

        System.out.println(title);
        while (!keys.isEmpty()) {
            int currentKey = keys.choose();
            keys.remove(currentKey);
            System.out.println(currentKey + ": " + dict.get(currentKey));
        }

        System.out.println("\n");
    }

    public static void printTreeInOrder(BinaryTreeADT tree, String title) {
        System.out.println(title);
        treeInOrderRecursive(tree);
        System.out.println("");
    }
    public static void treeInOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        treeInOrderRecursive(tree.getLeft());
        System.out.print(tree.getRoot() + ", ");
        treeInOrderRecursive(tree.getRight());
    }

    public static void printGraph(GraphADT graph, String title) {
        SetADT verts = graph.getVertxs();

        System.out.println(title);
        while (!verts.isEmpty()) {
            int element = verts.choose();
            verts.remove(element);
            System.out.print(element + ", ");
        }

        System.out.println("\n");
    }
}
