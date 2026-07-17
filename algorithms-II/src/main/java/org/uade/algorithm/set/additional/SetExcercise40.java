package org.uade.algorithm.set.additional;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

/**
 * AdditionalSetExcercise40
 * Dada una pila, utiliza un conjunto para identificar y eliminar los elementos duplicados
 * en la pila. Al final, la pila debe contener solo elementos unicos, conservando el orden.
 */
public class SetExcercise40 {
    public static void main(String[] args) {
        StackADT stack = new StaticStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(1);
        stack.add(2);

        System.out.println("Original:");
        printStack(stack);

        removeDuplicatesPreservingOrder(stack);

        System.out.println("Sin duplicados:");
        printStack(stack);
    }

    public static void removeDuplicatesPreservingOrder(StackADT stack) {
        SetADT seen = new StaticSetADT();
        StackADT reversed = getNewStack(stack);

        while (!stack.isEmpty()) {
            reversed.add(stack.getElement());
            stack.remove();
        }

        while (!reversed.isEmpty()) {
            int element = reversed.getElement();
            reversed.remove();

            if (!seen.exist(element)) {
                stack.add(element);
                seen.add(element);
            }
        }
    }

    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        }
        return new DynamicStackADT();
    }

    public static SetADT getNewSet(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static StackADT copyStack(StackADT stack) {
        StackADT aux = getNewStack(stack);
        StackADT copy = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux.add(stack.getElement());
            stack.remove();
        }

        while (!aux.isEmpty()) {
            int element = aux.getElement();
            copy.add(element);
            stack.add(element);
            aux.remove();
        }

        return copy;
    }

    public static void printStack(StackADT stack) {
        StackADT copy = copyStack(stack);
        StackADT reversed = getNewStack(stack);

        while (!copy.isEmpty()) {
            reversed.add(copy.getElement());
            copy.remove();
        }

        while (!reversed.isEmpty()) {
            System.out.print(reversed.getElement() + " ");
            reversed.remove();
        }
        System.out.println();
    }
}
