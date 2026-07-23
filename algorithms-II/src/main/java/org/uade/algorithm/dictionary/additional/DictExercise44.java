package org.uade.algorithm.dictionary.additional;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

/**
 * DictExercise44
 * Crear una aplicacion que dado una serie de elementos, podamos saber la cantidad
 * de ocurrencias del mismo.
 */
public class DictExercise44 {
    public static void main(String[] args) {
        SimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 3);
        dict.add(2, 5);
        dict.add(3, 10);

        printDict(dict);

        betterAdd(dict, 1, 7);
        betterAdd(dict, 2, 5);
        betterAdd(dict, 3, 5);
        betterAdd(dict, 5, 5);
        printDict(dict);
        
    }

    public static void betterAdd(SimpleDictionaryADT dict, int item, int itemCount) {
        if (itemCount < 0) {
            return;
        }
        SetADT keys = dict.getKeys();

        if (keys.exist(item)) {
            // Key already exists, add count
            int currentValue = dict.get(item);
            int newValue = currentValue + itemCount;
            dict.add(item, newValue);
        } else {
            // Create new key, set value/count to 0.
            dict.add(item, itemCount);
        }
    }

    public static SimpleDictionaryADT getNewDict(SimpleDictionaryADT dict) {
        if (dict instanceof StaticSimpleDictionaryADT) {
            return new StaticSimpleDictionaryADT();
        }
        return new DynamicSimpleDictionaryADT();
    }

    public static void printDict(SimpleDictionaryADT dict) {
        SetADT keys = dict.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            keys.remove(key);
            System.out.println(key + ": " + dict.get(key));
        }
        System.out.println();
    }
}
