package org.uade.algorithm.stack.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.StackADTUtil;

// Cree e inicialice la pila DADA. Pase el primer elemento(tope) de la pila DADA a su última
// posición(fondo), dejando los restantes elementos en el orden original.
public class AddExercise013 {
    public static void main(String[] args) {

        // DADA: 
        // 5 (tope)
        // 4
        // 3
        // 2
        // 1 (fondo)

        // Expected Result:
        // 4
        // 3
        // 2
        // 1
        // 5

        StackADT dada = new DynamicStackADT();
        // dada.add(1);
        // dada.add(2);
        // dada.add(3);
        // dada.add(4);
        // dada.add(5);

        System.out.println("DADA: ");
        print(dada);

        StackADT result = moveFirstValueToLastPosition(dada);
        System.out.println("Result: ");
        print(result);
        
    }

    // Move first value to last position
    public static StackADT moveFirstValueToLastPosition(StackADT stack) {
        if (stack.isEmpty()) {
            throw new EmptyADTException();
        }
        
        StackADT copyStack = copy(stack);
        int tope = copyStack.getElement(); // 5

        StackADT aux = getNewStack(stack);
        copyStack.remove();
        while (!copyStack.isEmpty()) {
            aux.add(copyStack.getElement());
            copyStack.remove();
        }

        StackADT result = getNewStack(stack);
        result.add(tope);
        while (!aux.isEmpty()) {
            result.add(aux.getElement());
            aux.remove();
        }

        return result;

    }

    // Factory
    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof DynamicStackADT) {
            return new DynamicStackADT();
        }
        return new StaticStackADT();
    }

    // Copy
    public static StackADT copy(StackADT stack) {
        StackADT aux1 = getNewStack(stack);
        StackADT aux2 = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux1.add(stack.getElement());
            aux2.add(stack.getElement());
            stack.remove();
        }

        // Re alimentamos stack para dejarlo base
        while (!aux1.isEmpty()) {
            stack.add(aux1.getElement());
            aux1.remove();
        }

        while (!aux2.isEmpty()) {
            aux1.add(aux2.getElement());
            aux2.remove();
        }

        return aux1;
    }

    // Print
    public static void print(StackADT stack) {
        StackADT stackCopy = copy(stack);

        while (!stackCopy.isEmpty()) {
            System.out.println(stackCopy.getElement());
            stackCopy.remove();
        }

    }

}
