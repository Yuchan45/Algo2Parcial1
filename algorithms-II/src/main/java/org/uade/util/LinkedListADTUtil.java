package org.uade.util;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;

public class LinkedListADTUtil {
    // Factory
    private static LinkedListADT getNewList(LinkedListADT list) {
        if (list instanceof DynamicLinkedListADT) {
            return new DynamicLinkedListADT();
        }
        return new StaticLinkedListADT();
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
        LinkedListADT copy = copy(list);

        for (int i = 0; i < copy.size(); i++) {
            System.out.print(copy.get(i));
            if (i != copy.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

}
