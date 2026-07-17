package org.uade.util;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;


public class SetADTUtil extends BaseUtil {
    // Copy
    public static SetADT copy(SetADT set) {
        SetADT copy = getNewSet(set);
        SetADT aux = getNewSet(set);

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

    // Print
    public static void print(SetADT set) {
        SetADT copy = copy(set);
        while(!copy.isEmpty()) {
            int element = copy.choose();
            System.out.print(element + " ");
            copy.remove(element);
        }
    }

    // Factory
    private static SetADT getNewSet(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        } else {
            return new DynamicSetADT();
        }
    }
}
