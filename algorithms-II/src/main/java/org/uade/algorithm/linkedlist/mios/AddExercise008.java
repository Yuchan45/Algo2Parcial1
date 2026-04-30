package org.uade.algorithm.linkedlist.mios;

import org.uade.exception.GenericADTException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.util.LinkedListADTUtil;

// Dada una lista y un número n, implementa un método que elimine los últimos n
// elementos de la lista.
public class AddExercise008 {
    public static void main(String[] args) {

        // List: [1, 2, 3, 4, 5]
        // n = 2
        // Expected Result: [1, 2, 3]
        int n = 3;
        LinkedListADT list = new DynamicLinkedListADT();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("n: " + n);
        System.out.println("Lista A: ");
        LinkedListADTUtil.print(list);

        LinkedListADT result = removeLastNValues(list, n);
        System.out.println("Result: ");
        LinkedListADTUtil.print(result);
    }

    public static LinkedListADT removeLastNValues(LinkedListADT list, int n) {
        if (n > list.size()) {
            throw new GenericADTException("N cant be greater than the list size");
        }
        LinkedListADT result = LinkedListADTUtil.copy(list);

        for (int i = result.size() -1; i >= 0; i--) {
            if (n > 0) {
                result.remove(i);
                n--;
            }
        }

        return result;
    }
}
