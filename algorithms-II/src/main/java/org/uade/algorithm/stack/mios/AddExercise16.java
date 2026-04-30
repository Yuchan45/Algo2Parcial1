package org.uade.algorithm.stack.mios;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.StackADTUtil;

// Cree, inicialice y la pila. Elimine los elementos repetidos de la misma dejando
// solamente un ejemplar de cada valor
public class AddExercise16 {
    public static void main(String[] args) {

        // Stack: [1, 2, 1, 3, 4, 4]
        // Expected result: [4, 3, 1, 2]
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(1);
        stack.add(3);
        stack.add(4);
        stack.add(4);

        System.out.println("Stack: ");
        StackADTUtil.print(stack);

        StackADT result = filterDuplicates(stack);
        System.out.println("Result: ");
        StackADTUtil.print(result);

    }

    public static StackADT filterDuplicates(StackADT stack) {
        StackADT copyStack = StackADTUtil.copy(stack);
        StackADT result = getNewStack(stack);

        while (!copyStack.isEmpty()) {
            int currentValue = copyStack.getElement();

            StackADT auxStack = StackADTUtil.copy(result);
            boolean exists = false;
            while (!auxStack.isEmpty()) {
                if (currentValue == auxStack.getElement()) {
                    exists = true;
                }
                auxStack.remove();
            }

            if (!exists) {
                result.add(currentValue);
            }

            copyStack.remove();
        }

        StackADT resultResult = getNewStack(stack);
        while (!result.isEmpty()) {
            resultResult.add(result.getElement());
            result.remove();
        }

        return resultResult;
    }

    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        }
        return new DynamicStackADT();
    }
}
