package org.uade.algorithm.dictionary.additional;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;
import org.uade.util.SetADTUtil;

/**
 * 47. Dado un Diccionario Simple, crea un Diccionario Multiple donde las claves sean los
 * valores únicos del diccionario simple, y los valores asociados sean las claves del
 * diccionario simple que tienen ese valor.
 * Ejemplo sencillo:
    Diccionario simple:
    - 1 -> 10
    - 2 -> 20
    - 3 -> 10
    - 4 -> 30
    - 5 -> 20

    Entonces el diccionario múltiple resultante sería:
    - 10 -> [1, 3]
    - 20 -> [2, 5]
    - 30 -> [4]
 */
public class DictExercise47 {
    public static void main(String[] args) {
        SimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 10);
        dict.add(2, 20);
        dict.add(3, 10);
        dict.add(4, 30);
        dict.add(5, 20);

        System.out.println("Original Dict");
        printSimpleDict(dict);
        
        MultipleDictionaryADT result = invertSimpleDict(dict);
        System.out.println("Result Dict");
        printMultiDict(result);

    }

    public static MultipleDictionaryADT invertSimpleDict(SimpleDictionaryADT dict) {
        MultipleDictionaryADT resultDict = new StaticMultipleDictionaryADT();
        SetADT keys = SetADTUtil.copy(dict.getKeys());

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int value = dict.get(key);
            keys.remove(key);
            resultDict.add(value, key);
        }

        return resultDict;
    }

    public static void printSimpleDict(SimpleDictionaryADT dict) {
        SetADT keys = SetADTUtil.copy(dict.getKeys());

        while (!keys.isEmpty()) {
            int key = keys.choose();
            System.out.println(key + " -> " + dict.get(key));
            keys.remove(key);
        }
    }

    public static void printMultiDict(MultipleDictionaryADT dict) {
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
