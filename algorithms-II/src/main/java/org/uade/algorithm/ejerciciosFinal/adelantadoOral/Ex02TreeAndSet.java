package org.uade.algorithm.ejerciciosFinal.adelantadoOral;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

/**
 * TreeAndSetEx02
 * Sea un arbol y un set, guardar en una cola los elementos que NO se compartan (diferencia simetrica)
 */
public class Ex02TreeAndSet {
    public static void main(String[] args) {
        BinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(1);
        // printTreeByLevel(tree);
        printTreeInOrder(tree);

        SetADT set = new StaticSetADT();
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);
        printSet(set, "Base Set: ");

        // Por diferencia simetrica, deberia quedar un set resultado con [1, 2, 3, 4, 5, 9, 10]
        SetADT result = getSymetricDifference(tree, set);
        printSet(result, "Result Set: ");

        
    }
    public static SetADT getSymetricDifference(BinaryTreeADT tree, SetADT baseSet) {
        // Para los ejercicios de diferencia son 2 pasos:
        SetADT result = new StaticSetADT();

        // Hay que iterar el set y quedarnos con los elementos que NO estan en el arbol
        SetADT set = copySet(baseSet);
        while (!set.isEmpty()) {
            boolean elementFoundInTree = false;
            int e = set.choose();
            elementFoundInTree = existsInTree(tree, e);
            if (!elementFoundInTree) {
                result.add(e);
            }
            set.remove(e);
        }

        // Hay que iterar el arbol y quedarnos con los elementos que NO estan en el set
        SetADT copySet = copySet(baseSet);
        existsInSet(tree, copySet, result);


        return result;
    }
    public static void existsInSet(BinaryTreeADT tree, SetADT set, SetADT result) {
        if (tree.isEmpty()) {
            return;
        }

        if (!set.exist(tree.getRoot())) {
            result.add(tree.getRoot());
        }
        existsInSet(tree.getLeft(), set, result);
        existsInSet(tree.getRight(), set, result);
    }


    public static boolean existsInTree(BinaryTreeADT tree, int searchValue) {
        if (tree.isEmpty()) {
            return false;
        }

        if (tree.getRoot() == searchValue) {
            return true;
        }

        return existsInTree(tree.getLeft(), searchValue)
            || existsInTree(tree.getRight(), searchValue);
    }


    public static SetADT getNewSet(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static SetADT copySet(SetADT set) {
        SetADT copy = getNewSet(set);
        SetADT aux = getNewSet(set);

        while (!set.isEmpty()) {
            int e = set.choose();
            copy.add(e);
            aux.add(e);
            set.remove(e);
        }

        while (!aux.isEmpty()) {
            int e = aux.choose();
            set.add(e);
            aux.remove(e);
        }

        return copy;
    }

    public static void printSet(SetADT set, String title) {
        SetADT setCopy = copySet(set);
        System.out.println(title);

        while (!setCopy.isEmpty()) {
            int e = setCopy.choose();
            System.out.print(e + ", ");
            setCopy.remove(e);
        }
        System.out.println("");
    }


    public static BinaryTreeADT getNewTree(BinaryTreeADT tree) {
        if (tree instanceof StaticBinaryTreeADT) {
            return new StaticBinaryTreeADT();
        }
        return new DynamicBinaryTreeADT();
    }

    /**
     * Print In-Order (L, N, R)
     */
    public static void iterateInOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        iterateInOrderRecursive(tree.getLeft());
        System.out.print(tree.getRoot() + " ");
        iterateInOrderRecursive(tree.getRight());
    }
    public static void printTreeInOrder(BinaryTreeADT tree) {
        System.out.println("Tree In-Order:");
        iterateInOrderRecursive(tree);
        System.out.println("");
    }



    /**
     * Print By Level
     */
    public static int getHeight(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return 0;
        }

        int heightLeft = getHeight(tree.getLeft());
        int heightRight = getHeight(tree.getRight());

        return Math.max(heightLeft, heightRight) + 1;
    }
    public static void printGivenLevel(BinaryTreeADT tree, int level) {
        if (tree.isEmpty()) {
            return;
        }

        if (level == 1) {
            System.out.print(tree.getRoot() + " ");
            return;
        }

        printGivenLevel(tree.getLeft(), level - 1);
        printGivenLevel(tree.getRight(), level - 1);

    }
    public static void printTreeByLevel(BinaryTreeADT tree) {
        int height = getHeight(tree);
        System.out.println("Tree by level:");
        for (int i = 1; i <= height; i++) {
            printGivenLevel(tree, i);
        }
        System.out.println();

    }
}
