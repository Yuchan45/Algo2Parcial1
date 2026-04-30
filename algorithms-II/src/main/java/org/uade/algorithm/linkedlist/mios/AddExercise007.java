package org.uade.algorithm.linkedlist.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.util.LinkedListADTUtil;

// Dado un arreglo de listas, implementa un metodo que las concatene en una sola lista,
// manteniendo el orden de cada lista.
public class AddExercise007 {
    public static void main(String[] args) {
        LinkedListADT listA = new DynamicLinkedListADT();
        listA.add(1);
        listA.add(2);
        listA.add(3);

        LinkedListADT listB = new DynamicLinkedListADT();
        listB.add(4);
        listB.add(5);
        listB.add(6);

        LinkedListADT listC = new DynamicLinkedListADT();
        listC.add(7);
        listC.add(8);
        listC.add(9);

        LinkedListADT[] listArray = {listA, listB, listC};

        LinkedListADT result = concatenateLists(listArray);

        System.out.println("Lista concatenada:");
        LinkedListADTUtil.print(result);
    }

    public static LinkedListADT concatenateLists(LinkedListADT[] lists) {
        LinkedListADT result = new DynamicLinkedListADT();

        for (int i = 0; i < lists.length; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                result.add(lists[i].get(j));
            }
        }

        return result;
    }
}
