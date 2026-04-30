package org.uade.algorithm.stack.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

// Dado una expresión matemática (con paréntesis, corchetes y llaves), escribe un
// programa que verifique si la expresión está balanceada. Utiliza una pila para hacer
// un seguimiento de los paréntesis y asegura que cada apertura tenga un cierre
// correspondiente en el orden correcto.

public class AddExercise014 {
    public static void main(String[] args) {
        String exp1 = "{[()]}";  // Balanceado
        String exp2 = "{[(])}";  // No balanceado
        String exp3 = "{[()]";   // No balanceado

        boolean isBalanced = isBalanced(exp1);

        if (isBalanced) {
            System.out.println("La expresion: " + exp1 + " esta balanceda.");
        } else {
            System.out.println("La expresion: " + exp1 + " NO esta balanceda.");
        }
        
    }

    // Funcion que determina si Balanceo
    public static boolean isBalanced(String expression) {
        



        return true;
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
        if (stack.isEmpty()) {
            return getNewStack(stack);
        }

        StackADT aux1 = getNewStack(stack);
        StackADT aux2 = getNewStack(stack);

        while (!stack.isEmpty()) {
            aux1.add(stack.getElement());
            aux2.add(stack.getElement());
            stack.remove();
        }

        // Re alimentamos stack para dejarlo como estaba
        while (!aux1.isEmpty()) {
            stack.add(aux1.getElement());
            aux1.remove();
        }

        // Alimentamos aux1 que va a ser la copia resultado final
        while (!aux2.isEmpty()) {
            aux1.add(aux2.getElement());
            aux2.remove();
        }

        return aux1;
    }

    // Print
    public static void print(StackADT stack) {
        StackADT copyStack = copy(stack);

        while (!copyStack.isEmpty()) {
            System.out.println(copyStack.getElement());
            copyStack.remove();
        }

    }

}
