package org.uade.algorithm.stack.mios;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

// Mostrar los elementos de una pila pero sin utilizar un while
public class AddExercise018 {
    public static void main(String[] args) {
        // Stack:
        // 4
        // 3
        // 2
        // 1
        // Result: 
        // 4
        // 3
        // 2
        // 1
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        recursivePrint(stack);
    }

    public static void recursivePrint(StackADT stack) {
        if (!stack.isEmpty()) {
            System.out.println(stack.getElement());
            stack.remove();
            recursivePrint(stack);
        }
    }

}
