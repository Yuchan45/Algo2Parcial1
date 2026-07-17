package org.uade.algorithm.set.additional;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

/**
 * SetExcercise42
 * Crear una aplicacion que dado dos conjuntos devuelva un nuevo conjunto que
 * contenga los elementos que están en uno u otro conjunto, pero no en ambos.
 */
public class SetExcercise42 {
    public static void main(String[] args) {
        SetADT set = new StaticSetADT();
        set.add(1);
        set.add(2);
        set.add(3);
     
        
        SetADT set2 = new StaticSetADT();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        
        printSet(set); // 1, 2, 3
        printSet(set2); // 3, 4, 5
        // Expected; 1, 2, 4, 5

        SetADT result = getSetDifference(set, set2);
        System.err.println("Result");
        printSet(result);

    }
    public static SetADT getSetDifference(SetADT set1, SetADT set2) {
        SetADT resultSet = getNewSet(set1);
        SetADT copySet1 = copySet(set1);
        SetADT copySet2 = copySet(set2);

        while (!copySet1.isEmpty()) {
            int elSet1 = copySet1.choose();
            if (!set2.exist(elSet1)) {
                resultSet.add(elSet1);
            }
            copySet1.remove(elSet1);
        }

        while (!copySet2.isEmpty()) {
            int elSet2 = copySet2.choose();
            if (!set1.exist(elSet2)) {
                resultSet.add(elSet2);
            }
            copySet2.remove(elSet2);
        }

        return resultSet;
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
