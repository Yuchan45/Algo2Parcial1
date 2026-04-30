package org.uade.algorithm.linkedlist.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;

// Dadas dos listas ordenadas, implementa un método que combine ambas en una
// nueva lista que mantenga el orden.
public class AddExercise003 {
    public static void main(String[] args) {

        // ListA: [1, 4, 5]
        // ListB: [2, 3, 6, 7]
        // Result: [1, 2, 3, 4, 5, 6, 7]
        LinkedListADT listA = new DynamicLinkedListADT();
        listA.add(1);
        listA.add(4);
        listA.add(5);
        System.out.println("Lista A:");
        print(listA); // O(n)

        LinkedListADT listB = new DynamicLinkedListADT();
        listB.add(2);
        listB.add(3);
        listB.add(6);
        listB.add(7);
        System.out.println("Lista B:");
        print(listB); // O(n)

        // Proceso
        LinkedListADT result = combinationList(listA, listB); // O(n) + O(n) + O(m) = O(n+m)
        System.out.println("Result:");
        print(result); // O(n)

        // Complejidad total: O(n+m)
    }

    // Combination
    public static LinkedListADT combinationList(LinkedListADT listA, LinkedListADT listB) {
        // Asumo que ambas listas son dynamicas.
        LinkedListADT result = new DynamicLinkedListADT();

        int i = 0, j = 0;

        while (i < listA.size() && j < listB.size()) {
            if (listA.get(i) <= listB.get(j)) {
                result.add(listA.get(i));
                i++;
            } else {
                result.add(listB.get(j));
                j++;
            }
        }

        while (i < listA.size()) {
            result.add(listA.get(i));
            i++;
        }

        while (j < listB.size()) {
            result.add(listB.get(j));
            j++;
        }

        return result;
    }

    // Factory
    public static LinkedListADT getNewList(LinkedListADT list) {
        if (list instanceof DynamicLinkedListADT) {
            return new DynamicLinkedListADT();
        } else {
            return new StaticLinkedListADT();
        }
    }

    // Copy
    public static LinkedListADT copy(LinkedListADT list) {
        LinkedListADT copy = getNewList(list);
        for (int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }
        return copy;
    }

    // Print
    public static void print(LinkedListADT list) {
        // Trabajamos sobre la copia
        LinkedListADT copy = copy(list);

        for (int i = 0; i < copy.size(); i++) {
            System.out.print(copy.get(i));
            if (i != copy.size() -1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }
}


