package org.uade.algorithm.ejerciciosFinal.adelantado;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

/**
 * AdelantadoEjercicio2
 * Hacer un diccionario simple con un rango de valores, verificar si las claves están
 * dentro de ese rango y pasar esos valores a un conjunto.
 */
public class AdelantadoEjercicio2 {
    public static void main(String[] args) {
        // Sea el rango: [2, 4]. (2 y 4 incluidos)
        // El el set resultado deberian quedar los VALORES de aquellas keys del dict que se encuentren dentro del rango.
        // Siguiendo los datos del ejemplo, el set deberia quedar con los valores: 20, 30 y 40

        SimpleDictionaryADT dict = new StaticSimpleDictionaryADT();
        dict.add(1, 10);
        dict.add(2, 20);
        dict.add(3, 30);
        dict.add(4, 40);
        dict.add(5, 50);
        System.out.println("Dict base:");
        printSimpleDict(dict);

        SetADT resultSet = filterDictByRange(dict, 2, 4);
        System.out.println("Result Set given the range: [2, 4]");
        printSet(resultSet);

    }

    public static SetADT filterDictByRange(SimpleDictionaryADT dict, int min, int max) {
        SetADT keys = dict.getKeys();
        SetADT filteredDictValues = new StaticSetADT();

        while (!keys.isEmpty()) {
            int currentKey = keys.choose();
            keys.remove(currentKey);
            int currentValue = dict.get(currentKey);
            if (currentKey >= min && currentKey <= max) {
                filteredDictValues.add(currentValue);
            }
        }

        return filteredDictValues;
    }

    public static SetADT getNewSet(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static SetADT copySet(SetADT set) {
        SetADT copy = new StaticSetADT();
        SetADT aux = new StaticSetADT();

        // Hacemos una copia en copy y aux en base al set original.
        while (!set.isEmpty()) {
            int element = set.choose();
            copy.add(element);
            aux.add(element);
            set.remove(element);
        }

        // Restauramos el original
        while (!aux.isEmpty()) {
            int element = aux.choose();
            set.add(element);
            aux.remove(element);
        }

        // Devolvemos la copia
        return copy;
    }

    public static void printSet(SetADT set) {
        SetADT copy = copySet(set);

        while (!copy.isEmpty()) {
            int element = copy.choose();
            System.out.print(element + ", ");
            copy.remove(element);
        }
        System.out.println("");
    }

    public static SimpleDictionaryADT getNewDict(SimpleDictionaryADT dict) {
        if (dict instanceof StaticSimpleDictionaryADT) {
            return new StaticSimpleDictionaryADT();
        }
        return new DynamicSimpleDictionaryADT();
    }

    public static void printSimpleDict(SimpleDictionaryADT dict) {
        SetADT keys = dict.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            keys.remove(key);
            System.out.println(key + ": " + dict.get(key));
        }
        System.out.println("");
    }


}
