package org.uade.algorithm.ejerciciosFinal;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

public class testDictsPrints {
    public static void main(String[] args) {
        SimpleDictionaryADT sDict = new StaticSimpleDictionaryADT();
        sDict.add(1, 10);
        sDict.add(2, 20);
        sDict.add(3, 30);
        printSDict(sDict);

        MultipleDictionaryADT mDict = new StaticMultipleDictionaryADT();
        mDict.add(1, 10);
        mDict.add(2, 20);
        mDict.add(2, 21);
        mDict.add(3, 30);
        mDict.add(3, 31);
        mDict.add(3, 32);
        printMDict(mDict);

    }

    public static void printSDict(SimpleDictionaryADT dict) {
        SetADT keys = dict.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            System.out.println(key + ": " + dict.get(key));
            keys.remove(key);
        }
        System.out.println("");
    }
    
    public static void printMDict(MultipleDictionaryADT dict) {
        SetADT keys = dict.getKeys();

        while (!keys.isEmpty()) {
            int key = keys.choose();
            int[] values = dict.get(key);
            System.out.print(key + ": [");
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.print("]\n");
            keys.remove(key);
        }
        System.out.println("");
    }

}
