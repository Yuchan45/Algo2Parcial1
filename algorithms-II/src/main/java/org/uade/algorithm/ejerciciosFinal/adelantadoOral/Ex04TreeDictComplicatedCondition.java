package org.uade.algorithm.ejerciciosFinal.adelantadoOral;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;
import org.uade.util.SetADTUtil;

/**
 * Ex04TreeDictComplicatedCondition
 * Tenes un arbol y un dict simple. Tenes que devolver los elementos de interseccion agregando la 
 * condicion de que se respeten las relaciones. 
 * Digamos que tenes en el arbol la relacion 10 -> 12 (hijo derecho). 
 * Entonces lo tenes que devolver sii en el dict esta la key 10: 12
 * Ejemplo simple
  Árbol:
      10
     /  \
    5    12
   /
  3

  Relaciones del árbol:
  - 10 -> 5
  - 10 -> 12
  - 5 -> 3

  Diccionario simple:
  - 10 -> 12
  - 5 -> 3
  - 20 -> 99

  Entonces, la intersección “respetando relaciones” sería:

  - 10 -> 12
  - 5 -> 3

  No estaría:

  - 10 -> 5, porque esa relación existe en el árbol pero no en el diccionario
  - 20 -> 99, porque existe en el diccionario pero no en el árbol
 */
public class Ex04TreeDictComplicatedCondition {
    public static void main(String[] args) {
        BinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(10);
        tree.add(5);
        tree.add(12);
        tree.add(3);
        //printTreeByLevel(tree, "Tree By Level");
        printTreeInOrder(tree, "Tree In-Order");


        SimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(10, 12);
        dict.add(5, 3);
        dict.add(20, 99);

        // Deberia obtener una lista con los NODOS de larbol que cumplen, osea [10, 5]
        SetADT result = getNodesWithMatchingRelations(tree, dict);
        System.out.println("Matching parent nodes:");
        SetADTUtil.print(result);
        System.out.println("");

    }

    public static SetADT getNodesWithMatchingRelations(BinaryTreeADT tree, SimpleDictionaryADT dict) {
        SetADT result = new StaticSetADT();
        collectNodesWithMatchingRelations(tree, dict, result);
        return result;
    }

    public static void collectNodesWithMatchingRelations(BinaryTreeADT tree, SimpleDictionaryADT dict, SetADT result) {
        if (tree.isEmpty()) {
            return;
        }

        int parentValue = tree.getRoot();
        BinaryTreeADT left = tree.getLeft();
        BinaryTreeADT right = tree.getRight();

        if (!left.isEmpty() && dictContainsRelation(dict, parentValue, left.getRoot())) {
            result.add(parentValue);
        }

        if (!right.isEmpty() && dictContainsRelation(dict, parentValue, right.getRoot())) {
            result.add(parentValue);
        }

        collectNodesWithMatchingRelations(left, dict, result);
        collectNodesWithMatchingRelations(right, dict, result);
    }

    public static boolean dictContainsRelation(SimpleDictionaryADT dict, int key, int expectedValue) {
        SetADT keys = dict.getKeys();
        return keys.exist(key) && dict.get(key) == expectedValue;
    }

    public static BinaryTreeADT getNewBinaryTree(BinaryTreeADT tree) {
        if (tree instanceof StaticBinaryTreeADT) {
            return new StaticBinaryTreeADT();
        }
        return new DynamicBinaryTreeADT();
    }

    public static void printTreeInOrder(BinaryTreeADT tree, String title) {
        System.out.println(title);
        iterateTreeInOrder(tree);
        System.out.println("");
    }

    // L, N, R
    public static void iterateTreeInOrder(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        iterateTreeInOrder(tree.getLeft());
        System.out.print(tree.getRoot() + " ");
        iterateTreeInOrder(tree.getRight());
    }


    public static int getTreeHeight(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return 0;
        }

        int leftHeight = getTreeHeight(tree.getLeft());
        int rightHeight = getTreeHeight(tree.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }
    public static void printLevel(BinaryTreeADT tree, int level) {
        if (tree.isEmpty()) {
            return;
        }

        if (level == 1) {
            System.out.print(tree.getRoot() + " ");
        }

        printLevel(tree.getLeft(), level - 1);
        printLevel(tree.getRight(), level - 1);

    }
    public static void printTreeByLevel(BinaryTreeADT tree, String title) {
        System.out.println(title);

        int height = getTreeHeight(tree);
        for (int i = 1; i <= height; i++) {
            printLevel(tree, i);
        }
        System.out.println("");
    }
}
