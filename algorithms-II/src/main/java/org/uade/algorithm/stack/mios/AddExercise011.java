package org.uade.algorithm.stack.mios;

import java.util.EmptyStackException;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.StackADTUtil;

// Cree e inicialice las pilas MOD y DADA. Elimine de la pila DADA todos los elementos
// que sean iguales a los elementos de la pila MOD.
public class AddExercise011 {
    public static void main(String[] args) {
        // mod: [1, 3, 5]
        // dada: [1, 2, 3, 4, 5]
        // result: [2, 4]

        StackADT mod = new DynamicStackADT();
        mod.add(1);
        mod.add(3);
        // mod.add(5);

        StackADT dada = new DynamicStackADT();
        dada.add(1);
        dada.add(2);
        dada.add(3);
        dada.add(4);
        dada.add(5);

        System.out.println("MOD: ");
        print(mod);

        System.out.println("DADA: ");
        print(dada);

        StackADT result = removeDuplicates(mod, dada);
        System.out.println("Result: ");
        print(result);

    
    }

    // Remove Stack Duplicates
    // Removes values from DADA that also exist in MOD
    public static StackADT removeDuplicates(StackADT mod, StackADT dada) {
        StackADT copyDada = copy(dada);
        StackADT aux = new DynamicStackADT();
        StackADT result = new DynamicStackADT();

        while (!copyDada.isEmpty()) {
            int currentValue = copyDada.getElement();
            copyDada.remove();

            boolean found = false;
            StackADT tempMod = copy(mod);

            while (!tempMod.isEmpty()) {
                if (currentValue == tempMod.getElement()) {
                    found = true;
                }
                tempMod.remove();
            }

            if (!found) {
                aux.add(currentValue);
            }
        }

        // Si retorno aux asi, queda al reves. tengo que rotarlo
        // return aux;
        while (!aux.isEmpty()) {
            result.add(aux.getElement());
            aux.remove();
        }

        return result;
    }


    // Factory
    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof DynamicStackADT) {
            return new DynamicStackADT();
        }
        return new StaticStackADT();
    }

    // Copy
    public static StackADT copy(StackADT stack) {
        StackADT aux1 = getNewStack(stack);
        StackADT aux2 = getNewStack(stack);
        
        // Copiamos en ambos auxiliares
        while (!stack.isEmpty()) {
            aux1.add(stack.getElement());
            aux2.add(stack.getElement());
            stack.remove();
        }

        // Re alimentamos el stack original
        while (!aux1.isEmpty()) {
            stack.add(aux1.getElement());
            aux1.remove();
        }

        // Copiamos por 2da vez el stack (queda en order correcto) en aux1.
        while (!aux2.isEmpty()) {
            aux1.add(aux2.getElement());
            aux2.remove();
        }

        return aux1;
    }

    // Print
    public static void print(StackADT stack) {
        StackADT copy = copy(stack);
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        while (!copy.isEmpty()) {
            System.out.print(copy.getElement());
            copy.remove();
        }
        System.out.println("");
    }
}
