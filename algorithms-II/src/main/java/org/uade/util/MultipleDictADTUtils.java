package org.uade.util;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;

public class MultipleDictADTUtils {

    public static MultipleDictionaryADT getNewDict(MultipleDictionaryADT dict) {
        if (dict instanceof StaticMultipleDictionaryADT) {
            return new StaticMultipleDictionaryADT();
        } else {
            return new DynamicMultipleDictionaryADT();
        }
    }

    public static void print(MultipleDictionaryADT dict) {
        SetADT keys = SetADTUtil.copy(dict.getKeys());

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int[] values = dict.get(key);

            System.out.print(key + " -> [");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i]);
                if (i < values.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            keys.remove(key);
        }
    }
}
