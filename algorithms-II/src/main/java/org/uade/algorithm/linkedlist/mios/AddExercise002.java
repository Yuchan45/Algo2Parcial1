package org.uade.algorithm.linkedlist.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;

// Implementa un método que reciba una lista y elimine los elementos duplicados,
// dejando únicamente la primera aparición de cada elemento
public class AddExercise002 {
    public static void main(String[] args) {
        LinkedListADT list = new DynamicLinkedListADT();
        // List: [1, 2, 3, 2, 4] --> Expected Result: [1, 2, 3, 4]
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);

        System.out.println("Original");
        print(list); // O(n + n) = O(n)
        LinkedListADT result = removeDuplicatesFromList(list); // O(n * m)
        System.out.println("Result");
        print(result);

        // Complejidad total: O(n) + O(n*m) = O(n*m)
    }

    // Remove duplicates
    public static LinkedListADT removeDuplicatesFromList(LinkedListADT list) {
        // Trabajamos sobre una copia
        LinkedListADT baseList = copy(list);
        LinkedListADT result = getNewList(list);

        // Recorro la lista original
        for (int i = 0; i < baseList.size(); i++) {
            // Tomo el valor de la iteracion
            int currentValue = baseList.get(i);
            boolean exists = false;

            // Recorro la lista resultado (uniqueValuesList)
            for (int j = 0; j < result.size(); j++) {
                // Si el valor de la iteracion se encuentra en la lista final, corto y marco que EXISTE
                if (currentValue == result.get(j)) {
                    exists = true;
                    break;
                }
                // Si no existe continuo hasta el final (dejando exists = false)
            }

            // Si NO marque que EXISTE, entonces debo agregarlo al resultado
            if (!exists) {
                result.add(currentValue);
            }
        }

        return result;
    }


    // Factory
    public static LinkedListADT getNewList(LinkedListADT list) {
        if (list instanceof DynamicLinkedListADT) {
            return new DynamicLinkedListADT();
        } else {
            return new StaticLinkedListADT();
        }
    }

    // Copy
    public static LinkedListADT copy(LinkedListADT list) {
        // Trabajamos sobre la copia
        LinkedListADT copy = getNewList(list);

        for (int i=0; i<list.size(); i++) {
            copy.add(list.get(i));
        }

        return copy;
    }

    // Print
    public static void print(LinkedListADT list) {
        // Trabajamos sobre la copia
        LinkedListADT copy = copy(list);
        if (copy.isEmpty()) {
            throw new EmptyADTException();
        }

        for (int i=0; i<copy.size(); i++) {
            System.out.print(copy.get(i));
            if (i < copy.size() -1) {
                System.out.print(", ");
            }
        }
        System.out.println("");

    }

}
