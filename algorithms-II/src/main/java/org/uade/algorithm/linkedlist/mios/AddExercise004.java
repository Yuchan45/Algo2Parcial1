package org.uade.algorithm.linkedlist.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.util.LinkedListADTUtil;

// Dadas dos listas, implementa un método que devuelva una nueva lista intercalando
// los elementos de ambas. Si una lista es más larga que la otra, los elementos
// restantes deben añadirse al final.

public class AddExercise004 {
    public static void main(String[] args) {
        // ListA: [1, 4, 5]
        // ListB: [2, 3, 6, 7, 8]
        // Result: [1, 2, 4, 3, 5, 6, 7, 8]
        LinkedListADT listA = new DynamicLinkedListADT();
        listA.add(1);
        listA.add(4);
        listA.add(5);
        System.out.println("Lista A:");
        LinkedListADTUtil.print(listA);

        LinkedListADT listB = new DynamicLinkedListADT();
        listB.add(2);
        listB.add(3);
        listB.add(6);
        listB.add(7);
        listB.add(8);
        System.out.println("Lista B:");
        LinkedListADTUtil.print(listB);

        // Proceso
        LinkedListADT result = intercalatedMerge(listA, listB);
        System.out.println("Result:");
        LinkedListADTUtil.print(result);


    }


    public static LinkedListADT intercalatedMerge(LinkedListADT listA, LinkedListADT listB) {
        // Asumo que ambos son Dynamicos
        LinkedListADT result = new DynamicLinkedListADT();

        int i = 0, j = 0;
        boolean turnA = true;

        while (i < listA.size() && j < listB.size()) {
            if (turnA) {
                result.add(listA.get(i));
                turnA = false;
                i++;
            } else {
                result.add(listB.get(j));
                turnA = true;
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
    
}
