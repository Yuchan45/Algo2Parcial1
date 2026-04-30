package org.uade.algorithm.stack.mios;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

//  Implementa un algoritmo que evalúe una expresión en notación postfija utilizando
//  una pila. 
// Por ejemplo, la expresión: 
//  3 4 + 2 * debería devolver 14
//  2 4 - 3 * deberia devolver -6
//  2 4 + 3 / deberia devolver 2
public class AddExercise015 {
    public static void main(String[] args) {
        String exp1 = "3 4 + 2 *"; // 14
        System.out.println("Expression: " + exp1);
        int result = calcPostfix(exp1);
        System.out.println("Resultado: " + result);
    }

    public static int calcPostfix(String expression) {
        String [] exp = expression.split(" ");
        int result = 0;

        StackADT stack = new DynamicStackADT();
        for (int i = 0; i < exp.length; i++) {
            String currentToken = exp[i];
            // System.out.println(currentToken);
            if (isNumber(currentToken)) {
                // Es numero, lo apilo
                stack.add(Integer.parseInt(currentToken));
            } else {
                // Es operacion. Desapilo los ultimos 2, aplico la operacion y vuelvo a apilar
                int value1 = stack.getElement();
                stack.remove();
                int value2 = stack.getElement();
                stack.remove();

                // Aplico la operacion
                result = switch (currentToken) {
                    case "+" -> value1 + value2;
                    case "-" -> value1 - value2;
                    case "*" -> value1 * value2;
                    case "/" -> value1 / value2;
                    default -> 0;
                };

                stack.add(result);
            }
        }

        return result;
    }

    public static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
