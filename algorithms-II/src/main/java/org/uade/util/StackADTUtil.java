package org.uade.util;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

public class StackADTUtil {

    // Factory
    public static StackADT getNewStack(StackADT stack) {
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
            aux.add(stack.getElement()); // 1
            stack.remove();
        }

        // Re armo el stack original
        while (!aux.isEmpty()) {
            stack.add(aux.getElement()); // 2
            copy.add(aux.getElement());  // 2
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
