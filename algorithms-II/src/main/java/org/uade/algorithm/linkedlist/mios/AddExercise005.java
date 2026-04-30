package org.uade.algorithm.linkedlist.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.util.LinkedListADTUtil;

// Dada una lista de números y un valor x, encuentra el elemento de la lista que esté
// más cerca de x
public class AddExercise005 {
    public static void main(String[] args) {
        // Suposiciones: 
        // Las listas solo contienen numeros enteros.
        // En caso de que 2 valores de la lista se encuentren a la misma "distancia" se tomara el menor
        // Ejemplo:
        // ListaB: [1, 3, 5] con value: 4
        // El sistema puede devolvera 3


        // List: [1, 5, 11]
        // Input value: 10
        // Result: 11
        int inputValue = 16;
        LinkedListADT listA = new DynamicLinkedListADT();
        listA.add(1);
        listA.add(5);
        listA.add(11);
        System.out.println("Input value: " + inputValue);
        System.out.println("Lista A: ");
        LinkedListADTUtil.print(listA);

        int closestValue = listClosestValue(listA, inputValue);
        System.out.println("Result: " + closestValue);
    }

    public static int listClosestValue(LinkedListADT list, int inputValue) {
        int closestValue = list.get(0);
        int minDistance = Math.abs(inputValue - closestValue);

        for (int i = 0; i < list.size(); i++) {
            int currentValue = list.get(i);
            int currentDistance = Math.abs(inputValue - currentValue);

            if (currentDistance < minDistance) {
                closestValue = currentValue;
                minDistance = currentDistance;
            }
        }


        return closestValue;
    }

}
