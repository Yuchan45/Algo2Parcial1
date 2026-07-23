package org.uade.algorithm.ejerciciosFinal.regularViernes;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

/**
 * RegularViernesEj2
 * Hacer un diccionario simple con un rango de valores, verificar si las claves están
 * dentro de ese rango y pasar esos valores a un conjunto. Sin usar while; 
 * utilizar recursividad.
 * Ejemplo:
 * Rango: [2, 4] incluidos
 * dict = {
 *  1: 10,
 *  2: 20,
 *  3: 30,
 *  4: 40,
 *  5: 50
 * }
 * 
 * Set: [20, 30, 40]
 */
public class RegularViernesEj2 {
    public static void main(String[] args) {
        // Init
        int min = 2;
        int max = 3;

        StaticSimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 10);
        dict.add(2, 20);
        dict.add(3, 30);
        dict.add(4, 40);
        dict.add(5, 50);

        printDict(dict, "Simple Dict: ");

        // Process
        SetADT resultSet = new StaticSetADT();
        SetADT keys = dict.getKeys();

        getDictValuesInRange(resultSet, dict, keys, min, max);

        printSet(resultSet, "Result Set: ");

    }

    public static void getDictValuesInRange(SetADT resultSet, SimpleDictionaryADT dict, SetADT keys, int min, int max) {
        if (dict.isEmpty()) {
            return;
        }

        int key = keys.choose();
        int value = dict.get(key);
        dict.remove(key);
        keys.remove(key);

        if (key >= min && key <= max) {
            resultSet.add(value);
        }

        getDictValuesInRange(resultSet, dict, keys, min, max);
    }

    public static void printDict(SimpleDictionaryADT dict, String title) {
        SetADT keys = dict.getKeys();
        
        System.out.println(title);
        System.out.println("Key | Value");
        while (!keys.isEmpty()) {
            int key = keys.choose();
            keys.remove(key);
            System.out.println(key + " | " + dict.get(key));
        }

        System.out.println("\n");
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
            int e = set.choose();
            set.remove(e);
            copy.add(e);
            aux.add(e);
        }

        while (!aux.isEmpty()) {
            int e = aux.choose();
            aux.remove(e);
            set.add(e);
        }

        return copy;
    }

    public static void printSet(SetADT set, String title) {
        SetADT copySet = copySet(set);

        System.out.println(title);
        while (!copySet.isEmpty()) {
            int e = copySet.choose();
            copySet.remove(e);
            System.out.print(e + ", ");
        }

        System.out.println("\n");
    }
}
