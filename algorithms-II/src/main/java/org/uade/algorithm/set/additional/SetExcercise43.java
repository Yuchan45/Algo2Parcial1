package org.uade.algorithm.set.additional;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

/**
 * SetExcercise43
 * Dado un conjunto, se busca que se creen 2 subconjuntos cada uno tendra la mitad
 * de los valores del conjunto original.
 */
public class SetExcercise43 {
    public static void main(String[] args) {
        SetADT set = new StaticSetADT();
        set.add(0);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        SplitResult result = splitSetInHalf(set);

        System.out.println("First Half:");
        SetADT firstHalf = result.firstHalf();
        printSet(firstHalf);
        System.out.println("Second Half:");
        SetADT secondfHalf = result.secondHalf();
        printSet(secondfHalf);

    }

    // Creo esto para envolver 2 sets en un solo objeto y asi la funcion puede devolverlo.
    public record SplitResult(SetADT firstHalf, SetADT secondHalf) {}

    public static SplitResult splitSetInHalf(SetADT set) {
        SetADT copySet = copySet(set);
        SetADT firstHalf = getNewSet(set);
        SetADT seconfHalf = getNewSet(set);

        // Agregar contador, recorrer el set. Si el contador es par va a uno, si es impar va a otro.
        int i = 0;
        while (!copySet.isEmpty()) {
            int elem = copySet.choose();
            if (i % 2 == 0) {
                firstHalf.add(elem);
            } else {
                seconfHalf.add(elem);
            }
            copySet.remove(elem);
            i++;
        }

        return new SplitResult(firstHalf, seconfHalf);
    }

    public static SetADT getNewSet(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static SetADT copySet(SetADT set) {
        SetADT copy = getNewSet(set);
        SetADT aux = getNewSet(set);

        while (!set.isEmpty()) {
            int element = set.choose();
            copy.add(element);
            aux.add(element);
            set.remove(element);
        }

        while (!aux.isEmpty()) {
            int element = aux.choose();
            set.add(element);
            aux.remove(element);
        }

        return copy;
    }

    public static void printSet(SetADT set) {
        SetADT copy = copySet(set);

        while (!copy.isEmpty()) {
            int e = copy.choose();
            System.out.print(e + " ");
            copy.remove(e);
        }
        System.out.println();

    }
}
