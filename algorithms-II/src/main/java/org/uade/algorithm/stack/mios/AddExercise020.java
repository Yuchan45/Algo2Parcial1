package org.uade.algorithm.stack.mios;

import java.util.Stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.StackADTUtil;

// Cree e inicialice la pila stack con al menos 4 elementos. 
// Intercambie la mitad superior con la mitad inferior de la pila.
// Supuestos:
// Si [1, 2, 3, 4] -> inf: [1, 2] ; sup: [3, 4]
// Si [1, 2, 3, 4, 5] -> inf: [1, 2] ; sup: [4, 5]. El 2 queda igual
public class AddExercise020 {
    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        // Stack par
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        stack.add(6);

        // Stack Impar
        // stack.add(1);
        // stack.add(2);
        // stack.add(3);
        // stack.add(4);
        // stack.add(5);

        System.out.println("Stack: ");
        StackADTUtil.print(stack);

        StackADT[] results = splitStackInMiddle(stack);
        
        StackADT inf = results[0];
        StackADT sup = results[1];
        System.out.println("Sup: ");
        StackADTUtil.print(sup);
        System.out.println("Inf: ");
        StackADTUtil.print(inf);

        System.out.println("Final Result: ");
        StackADT result = mergeStacks(sup, inf);
        StackADTUtil.print(result);

    }


    public static StackADT[] splitStackInMiddle(StackADT stack) {
        StackADT copyStack = StackADTUtil.copy(stack);
        StackADT inf = getNewStack(stack);
        StackADT sup = getNewStack(stack);

        int copyStackSize = size(copyStack);
        if (copyStackSize % 2 == 0) {
            // Par. [1, 2, 3, 4, 5, 6] -> inf: [1, 2, 3] ; sup: [4, 5, 6]
            int count = 0;
            while (!copyStack.isEmpty()) {
                if (count < (copyStackSize/2)) {
                    sup.add(copyStack.getElement());
                } else {
                    inf.add(copyStack.getElement());
                }
                count++;
                copyStack.remove();
            }
        } else {
            // Impar. [1, 2, 3, 4, 5] -> inf: [1, 2] ; sup: [4, 5]. El 2 queda igual
            int middleValueIndex = copyStackSize/2;
            int count = 0;
            while (!copyStack.isEmpty()) {
                if (count < (middleValueIndex+1)) {
                    sup.add(copyStack.getElement());
                } else {
                    inf.add(copyStack.getElement());
                }
                count++;
                copyStack.remove();
            }
        }

        // Doy vuelta inf y sup
        StackADT inf2 = reverse(inf);
        StackADT sup2 = reverse(sup);
        StackADT[] results = {inf2, sup2};

        return results;
    }

    public static StackADT mergeStacks(StackADT stack1, StackADT stack2) {
        StackADT aux1 = StackADTUtil.copy(stack1);
        StackADT copyStack1 = getNewStack(stack1);
        StackADT aux2 = StackADTUtil.copy(stack2);
        StackADT copyStack2 = getNewStack(stack2);
        StackADT result = getNewStack(stack1);

        while (!aux1.isEmpty()) {
            copyStack1.add(aux1.getElement());
            aux1.remove();
        }

        while (!copyStack1.isEmpty()) {
            result.add(copyStack1.getElement());
            copyStack1.remove();
        }

        while (!aux2.isEmpty()) {
            copyStack2.add(aux2.getElement());
            aux2.remove();
        }

        while (!copyStack2.isEmpty()) {
            result.add(copyStack2.getElement());
            copyStack2.remove();
        }

        return result;
    }

    public static StackADT reverse(StackADT stack) {
        StackADT copyStack = StackADTUtil.copy(stack);
        StackADT result = getNewStack(stack);

        while (!copyStack.isEmpty()) {
            result.add(copyStack.getElement());
            copyStack.remove();
        }
        return result;
    }

    public static int size(StackADT stack) {
        StackADT copyStack = StackADTUtil.copy(stack);
        int count = 0;
        while (!copyStack.isEmpty()) {
            count++;
            copyStack.remove();
        }
        return count;
    }

    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        }
        return new DynamicStackADT();
    }

}
