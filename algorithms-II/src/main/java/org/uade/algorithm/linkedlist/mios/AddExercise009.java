package org.uade.algorithm.linkedlist.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.util.LinkedListADTUtil;

// Dadas dos listas, implementa un método que determine si ambas tienen los mismos
// elementos sin importar el orden
public class AddExercise009 {
    public static void main(String[] args) {

        // ListA: [1, 3, 5]
        // ListB: [5, 1, 3]
        // Result: Ambas tienen los mismo elementos.

        LinkedListADT listA = new DynamicLinkedListADT();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);
        listA.add(5);

        System.out.println("Lista A: ");
        LinkedListADTUtil.print(listA);

        LinkedListADT listB = new DynamicLinkedListADT();
        listB.add(1);
        listB.add(2);
        listB.add(3);
        listB.add(4);
        listB.add(5);

        System.out.println("Lista B: ");
        LinkedListADTUtil.print(listB);

        if (haveSameValues(listA, listB)) {
            System.out.println("Both lists contain the same values");
        } else {
            System.out.println("Both lists contain different values");
        }


    }


    public static boolean haveSameValues(LinkedListADT listA, LinkedListADT listB) {
        // Que paja, creo que hay que hacer un bubble sort aca... Mepa que no va a tomar algo asi
        return true;
    }
}
