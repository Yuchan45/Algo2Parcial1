package org.uade.algorithm.linkedlist.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;
// import org.uade.util.LinkedListADTUtil;
import org.uade.util.LinkedListADTUtil;

// Crea un método que reciba una lista y devuelva una nueva lista con los mismos
// elementos pero en orden inverso.

public class AddExercise001 {
    public static void main(String[] args) {

        LinkedListADT original = new DynamicLinkedListADT();
        original.add(1);
        original.add(2);
        original.add(3);

        System.out.println("Original:");
        LinkedListADTUtil.print(original); // O(n)
        LinkedListADT copy = copy(original); // O(n)
        LinkedListADT reverseResultList = reverseList(copy); // O(n)

        System.out.println("Inversa:");
        LinkedListADTUtil.print(reverseResultList);

        // Complejidad total: O(n + n + n) = O(n)
    }

    // Funcion que recibe una lista y 
    // devuelve otra lista (que esta invertida a la original)
    public static LinkedListADT reverseList(LinkedListADT listaOriginal) {
        LinkedListADT reverseList = getNewLinkedList(listaOriginal);

        for (int i=(listaOriginal.size() -1); i >= 0; i--) {
            reverseList.add(listaOriginal.get(i));
        }
        
        return reverseList;
    }

    // Copy
    public static LinkedListADT copy(LinkedListADT list) {
        LinkedListADT copy = getNewLinkedList(list);
        for (int i=0; i<list.size(); i++) {
            copy.add(list.get(i));
        }

        return copy;
    }

    // Print
    public static void print(LinkedListADT list) {
        if (list.isEmpty()) {
            throw new EmptyADTException();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }

    }

    // Factory
    public static LinkedListADT getNewLinkedList(LinkedListADT list) {
        if (list instanceof StaticLinkedListADT) {
            return new StaticLinkedListADT();
        } else {
            return new DynamicLinkedListADT();
        }
    }


}


