package org.uade.algorithm.set.additional;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.SetADTUtil;
import org.uade.util.StackADTUtil;

/*
 * Additional Set Exercise 39
 * Dadas una pila y un conjunto, implementa un algoritmo que determine si ambos
 * contienen exactamente los mismos elementos, independientemente del orden.
 */
public class SetExcercise39 {
    public static void main(String[] args) {
        // Implement your solution here
        StackADT stack = new StaticStackADT();
        SetADT set = new StaticSetADT();
        
        stack.add(1);
        stack.add(2);
        stack.add(3);
        
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        print(set);

        boolean areEqual = areStackAndSetEqual(stack, set);
        if (areEqual) {
            System.out.println("La pila y el conjunto contienen exactamente los mismos elementos.");
        } else {
            System.out.println("La pila y el conjunto NO contienen exactamente los mismos elementos.");
        }
    }
    public static boolean areStackAndSetEqual(StackADT stack, SetADT set) {
        // Si la cantidad de elementos es diferente, no pueden ser iguales
        if (countStackValues(stack) != countSetValues(set)) {
            return false;
        }

        if (!verifyStackValuesAreInSet(stack, set)) {
            return false;
        }
        
        return true;
    }

    public static int countStackValues(StackADT stack) {
        StackADT auxStack = StackADTUtil.copy(stack);
        int count = 0;

        while(!auxStack.isEmpty()) {
            auxStack.remove();
            count++;
        }

        return count;
    }

    public static int countSetValues(SetADT set) {
        SetADT auxSet = SetADTUtil.copy(set);
        int count = 0;

        while(!auxSet.isEmpty()) {
            int element = auxSet.choose();
            auxSet.remove(element);
            count++;
        }

        return count;
    }


    public static boolean verifyStackValuesAreInSet(StackADT stack, SetADT set) {
        // Verificar si los elementos de la pila están en el conjunto
        StackADT auxStack = StackADTUtil.copy(stack);

        while(!auxStack.isEmpty()) {
            int element = auxStack.getElement();
            if (!set.exist(element)) {
                return false;
            }
            auxStack.remove();
        }

        return true;
    }


    public static SetADT copy(SetADT set) {
        SetADT copy = new StaticSetADT();
        SetADT aux = new StaticSetADT();

        while(!set.isEmpty()) {
            int element = set.choose();
            copy.add(element);
            aux.add(element);
            set.remove(element);
        }

        while(!aux.isEmpty()) {
            int element = aux.choose();
            set.add(element);
            aux.remove(element);
        }

        return copy;
    }

    public static void print(SetADT set) {
        SetADT copy = copy(set);
        while(!copy.isEmpty()) {
            int element = copy.choose();
            System.out.print(element + " ");
            copy.remove(element);
        }
        System.out.println();
    }
}

