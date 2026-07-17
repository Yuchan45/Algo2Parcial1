package org.uade.util;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

public class DictADTUtils {

    public static SimpleDictionaryADT getNewDict(SimpleDictionaryADT dict) {
        if (dict instanceof StaticSimpleDictionaryADT) {
            return new StaticSimpleDictionaryADT();
        } else {
            return new DynamicSimpleDictionaryADT();
        }
    }

    public static void print(SimpleDictionaryADT dict) {
        SetADT keys = SetADTUtil.copy(dict.getKeys());

        while (!keys.isEmpty()) {
            int key = keys.choose();
            System.out.println(key + " -> " + dict.get(key));
            keys.remove(key);
        }
    }
}
