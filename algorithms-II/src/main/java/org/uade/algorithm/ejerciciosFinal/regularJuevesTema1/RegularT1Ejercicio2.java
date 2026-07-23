package org.uade.algorithm.ejerciciosFinal.regularJuevesTema1;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

/**
 * RegularT1Ejercicio2
 * Dados dos sets, devolver otro set con los elementos en común y los elementos
 * mayores a un número. Evitar usar while; usar recursividad
 */
public class RegularT1Ejercicio2 {
    public static void main(String[] args) {
        // Init
        SetADT set1 = new StaticSetADT();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        SetADT set2 = new StaticSetADT();
        set2.add(2);
        set2.add(3);
        set2.add(4);

        printSet(set1, "Set1: ");
        printSet(set2, "Set2: ");

        // Process.
        SetADT result = new StaticSetADT();
        SetADT setA = copySet(set1);
        SetADT setB = copySet(set2);
        getUnionAndGt(result, setA, setB, 2);

        // Deberia devolver: [3, 4]
        printSet(result, "Result: ");
    }

    public static void getUnionAndGt(SetADT result, SetADT set1, SetADT set2, int n) {
        if (set1.isEmpty() || set2.isEmpty()) {
            return;
        }

        int set1Value = set1.choose();
        set1.remove(set1Value);

        if (set2.exist(set1Value) && set1Value > n) {
            result.add(set1Value);
        }

        getUnionAndGt(result, set1, set2, n);
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
            set.remove(element);
            copy.add(element);
            aux.add(element);
        }

        while (!aux.isEmpty()) {
            int element = aux.choose();
            aux.remove(element);
            set.add(element);
        }

        return copy;
    }

    public static void printSet(SetADT set, String title) {
        SetADT copy = copySet(set);
        System.out.println(title);

        while (!copy.isEmpty()) {
            int element = copy.choose();
            copy.remove(element);
            System.out.print(element + ", ");
        }
        System.out.println("\n");
    }

}
