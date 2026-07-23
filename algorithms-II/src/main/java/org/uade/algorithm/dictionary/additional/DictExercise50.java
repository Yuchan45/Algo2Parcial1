package org.uade.algorithm.dictionary.additional;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;
import org.uade.util.SetADTUtil;

/**
 * DictExercise50
 * 50. Implementa un método que compare un Diccionario Simple con un
 * DiccionarioMultiple y devuelva un informe que indique:
 *  ○ Claves únicas en cada diccionario.
 *  ○ Claves comunes con valores idénticos.
 *  ○ Claves comunes con valores distintos.
 */
public class DictExercise50 {
    public static void main(String[] args) {
        SimpleDictionaryADT simpleDict = new StaticSimpleDictionaryADT();
        simpleDict.add(1, 10);
        simpleDict.add(2, 20);
        simpleDict.add(3, 30);
        simpleDict.add(4, 40);

        MultipleDictionaryADT multipleDict = new StaticMultipleDictionaryADT();
        multipleDict.add(2, 20);
        multipleDict.add(3, 300);
        multipleDict.add(3, 301);
        multipleDict.add(4, 40);
        multipleDict.add(4, 41);
        multipleDict.add(5, 50);

        System.out.println("Diccionario simple:");
        printSimpleDict(simpleDict);
        System.out.println();

        System.out.println("Diccionario multiple:");
        printMultiDict(multipleDict);
        System.out.println();

        System.out.println("Ejemplo esperado para el informe:");
        System.out.println("Claves unicas en simple: [1] (La clave 1 solo existe en el SimpleDict)");
        System.out.println("Claves unicas en multiple: [5] (La clasve 5 solo existe en el MultiDict)");
        System.out.println("Claves comunes con valores identicos: [2]... (20 y 40)");
        System.out.println("Claves comunes con valores distintos: [3, 4]... (300, 301 | 40, 41 son claves de las keys 3 y 4, y NO estan en SimpleDict)");

        System.out.println();
        System.out.println("Resolucion:");
        System.out.println("Claves unicas en simple:");
        // Funcion 1
        System.out.println("Claves unicas en multiple:");
        // Funcion 2
        System.out.println("Claves comunes con valores identicos:");
        // Funcion 3
        System.out.println("Claves comunes con valores distintos:");
        // Funcion 4
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
