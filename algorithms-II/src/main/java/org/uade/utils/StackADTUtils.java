package org.uade.utils;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

public class StackADTUtils {
    // Factory
    private static StackADT getNewStack(StackADT stack) {
        if (stack instanceof DynamicStackADT) {
            return new DynamicStackADT();
        }
        return new StaticStackADT();
    }

    // Copy
    public static StackADT copy(StackADT stack) {
        StackADT copy = getNewStack(stack);
        StackADT aux = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux.add(stack.getElement());
            stack.remove();
        }

        // Re alimentamos stack y reapliamos para generar la copia bien
        while (!aux.isEmpty()) {
            copy.add(aux.getElement());
            stack.add(aux.getElement());
            aux.remove();
        }

        return copy;
    }

    // Print
    public static void print(StackADT stack) {
        StackADT copy = copy(stack);

        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }


}
