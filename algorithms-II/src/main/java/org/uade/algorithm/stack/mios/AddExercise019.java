package org.uade.algorithm.stack.mios;

import javax.print.DocFlavor.STRING;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.StackADTUtil;

// Cree, inicialice y cargue la pila stack, multiplique todos los elementos por un
// numero en particular (n)
public class AddExercise019 {
    public static void main(String[] args) {
        int n = 3;
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);

        System.out.println("Stack: with n=" + n);
        StackADTUtil.print(stack);

        StackADT result = multiplyStack(stack, n);
        System.out.println("Result: ");
        StackADTUtil.print(result);

    }


    public static StackADT multiplyStack(StackADT stack, int n) {
        StackADT copyStack = StackADTUtil.copy(stack);
        StackADT result = getNewStack(stack);
        StackADT aux = getNewStack(stack);

        while (!copyStack.isEmpty()) {
            int currentValue = copyStack.getElement();
            int calculatedValue = currentValue * n;
            
            aux.add(calculatedValue);
            copyStack.remove();
        }

        // Lo volvemos a dar vuelta
        while (!aux.isEmpty()) {
            result.add(aux.getElement());
            aux.remove();
        }

        return result;
    }

    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        }
        return new DynamicStackADT();
    }
}
