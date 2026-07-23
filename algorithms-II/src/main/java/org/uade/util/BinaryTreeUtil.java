package org.uade.util;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;

public class BinaryTreeUtil {

    public static BinaryTreeADT getNewTree(BinaryTreeADT tree) {
        if (tree instanceof StaticBinaryTreeADT) {
            return new StaticBinaryTreeADT();
        } else {
            return new DynamicBinaryTreeADT();
        }
    }

    // Default is InOrder
    public static void print(BinaryTreeADT tree) {
        printInOrder(tree);
    }

    // In: (L, N, R)
    public static void printInOrder(BinaryTreeADT tree) {
        printInOrderRecursive(tree);
        System.out.println();
    }
    private static void printInOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        printInOrderRecursive(tree.getLeft());
        System.out.print(tree.getRoot() + " ");
        printInOrderRecursive(tree.getRight());
    }

    // Pre: (N, L, R)
    public static void printPreOrder(BinaryTreeADT tree) {
        printPreOrderRecursive(tree);
        System.out.println();
    }
    private static void printPreOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        System.out.print(tree.getRoot() + " ");
        printPreOrderRecursive(tree.getLeft());
        printPreOrderRecursive(tree.getRight());
    }
    

    // Post: (L, R, N)
    public static void printPostOrder(BinaryTreeADT tree) {
        printPostOrderRecursive(tree);
        System.out.println();
    }
    private static void printPostOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        printPostOrderRecursive(tree.getLeft());
        printPostOrderRecursive(tree.getRight());
        System.out.print(tree.getRoot() + " ");
    }
    

    // Level-order: root, then each level from left to right
    public static void printByLevel(BinaryTreeADT tree) {
        int height = height(tree);

        for (int level = 1; level <= height; level++) {
            printGivenLevel(tree, level);
        }
        System.out.println();
    }
    private static int height(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return 0;
        }

        int leftHeight = height(tree.getLeft());
        int rightHeight = height(tree.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }
    private static void printGivenLevel(BinaryTreeADT tree, int level) {
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

}
