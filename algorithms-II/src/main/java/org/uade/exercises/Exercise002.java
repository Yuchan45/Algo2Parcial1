package org.uade.exercises;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

/**
 * Exercise002
 * Dado un diccionario Simple y un rango MIN, MAX
 * Verificar que las claves del diccionario se encuentren dentro del rango, y en caso de estarlos
 * guardarlos en un set.
 * Debe ser utilizando recursividad.
 */
public class Exercise002 {
    public static void main(String[] args) {
        int MIN = 2;
        int MAX = 5;
        SimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 10);
        dict.add(2, 20);
        dict.add(3, 30);
        dict.add(4, 40);
        dict.add(5, 50);
        dict.add(6, 60);
        printDict(dict, "Dict original: ");
        System.out.println("");

        // El resultado deberia ser un set con: [20, 30, 40, 50] que son los valores del conjunto cuya
        // key esta dentro del rango [2, 5].
        SetADT result = new StaticSetADT();
        getKeysInRange(dict, MIN, MAX, result); // O(n) 
        printSet(result, "Result set: "); // O(m) proque el print itera todos los m elems del set. n != m

        // La complejidad algoritmica es: O(n + m).
    }

    // Es O(n) porque debo iterar todas las keys del dict.
    public static void getKeysInRange(SimpleDictionaryADT dict, int min, int max, SetADT result) {
        SetADT keys = dict.getKeys(); // Asi evito obetner las keys en cada iteracion del stack de recursion
        // Como paso las keys, NO afecto el diccionario original pues no estoy removiendo las key-value de este.
        iterateKeys(dict, keys, min, max, result);
    }

    public static void iterateKeys(SimpleDictionaryADT dict, SetADT keys, int min, int max, SetADT result) {
        if (keys.isEmpty()) {
            return;
        }
        
        int key = keys.choose();
        // System.out.println("key: " + key);
        keys.remove(key);

        if (key >= min && key <= max) {
            // System.out.println("entro");
            result.add(dict.get(key));
        }

        iterateKeys(dict, keys, min, max, result);
    }


    public static void printDict(SimpleDictionaryADT dict, String title) {
        System.out.println(title);
        SetADT keys = dict.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            keys.remove(key);
            System.out.println(key + ": " + dict.get(key));
        }
        System.out.println("");
    }

    public static SetADT getNewSet(SetADT baseSet) {
        if (baseSet instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static SetADT copySet(SetADT set) {
        SetADT copy = getNewSet(set);
        SetADT aux = getNewSet(set);

        while (!set.isEmpty()) {
            int value = set.choose();
            set.remove(value);
            copy.add(value);
            aux.add(value);
        }

        while (!aux.isEmpty()) {
            int value = aux.choose();
            aux.remove(value);
            set.add(value);
        }

        return copy;
    }

    public static void printSet(SetADT baseSet, String title) {
        SetADT set = copySet(baseSet);

        System.out.println(title);
        while (!set.isEmpty()) {
            int value = set.choose();
            set.remove(value);
            System.out.print(value + " ");
        }
        System.out.println("");
    }


}
